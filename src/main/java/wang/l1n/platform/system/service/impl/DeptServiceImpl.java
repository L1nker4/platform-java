package wang.l1n.platform.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.l1n.platform.common.entity.ForestConstant;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.Tree;
import wang.l1n.platform.common.utils.SortUtil;
import wang.l1n.platform.common.utils.TreeUtil;
import wang.l1n.platform.system.dao.DeptMapper;
import wang.l1n.platform.system.entity.Dept;
import wang.l1n.platform.system.service.DeptService;

import java.util.*;

@Slf4j
@Service("deptService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {


    @Override
    public Map<String, Object> findDepts(QueryRequest request, Dept dept) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<Dept> depts = findDepts(dept, request);
            List<Tree<Dept>> trees = new ArrayList<>();
            buildTrees(trees, depts);
            Tree<Dept> deptTree = TreeUtil.build(trees);

            result.put("rows", deptTree);
            result.put("total", depts.size());
        } catch (Exception e) {
            log.error("获取部门列表失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return result;
    }

    @Override
    public List<Dept> findDepts(Dept dept, QueryRequest request) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dept.getDeptName()))
            queryWrapper.lambda().eq(Dept::getDeptName, dept.getDeptName());
        if (StringUtils.isNotBlank(dept.getCreateTimeFrom()) && StringUtils.isNotBlank(dept.getCreateTimeTo()))
            queryWrapper.lambda()
                    .ge(Dept::getCreateTime, dept.getCreateTimeFrom())
                    .le(Dept::getCreateTime, dept.getCreateTimeTo());
        SortUtil.handleWrapperSort(request, queryWrapper, "sort", ForestConstant.ORDER_ASC, true);
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional
    public void createDept(Dept dept) {
        Long parentId = dept.getParentId();
        if (parentId == null) {
            dept.setParentId(0L);
        }
        dept.setCreateTime(new Date());
        this.save(dept);
    }

    @Override
    @Transactional
    public void updateDept(Dept dept) {
        dept.setUpdateTime(new Date());
        this.baseMapper.updateById(dept);
    }

    @Override
    @Transactional
    public void deleteDepts(String[] deptIds) {
        this.delete(Arrays.asList(deptIds));
    }

    private void buildTrees(List<Tree<Dept>> trees, List<Dept> depts) {
        depts.forEach(dept -> {
            Tree<Dept> tree = new Tree<>();
            tree.setId(dept.getDeptId().toString());
            tree.setKey(tree.getId());
            tree.setParentId(dept.getParentId().toString());
            tree.setText(dept.getDeptName());
            tree.setCreateTime(dept.getCreateTime());
            tree.setUpdateTime(dept.getUpdateTime());
            tree.setOrder(dept.getSort());
            tree.setTitle(tree.getText());
            tree.setValue(tree.getId());
            trees.add(tree);
        });
    }

    private void delete(List<String> deptIds) {
        removeByIds(deptIds);

        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Dept::getParentId, deptIds);
        List<Dept> depts = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(depts)) {
            List<String> deptIdList = new ArrayList<>();
            depts.forEach(d -> deptIdList.add(String.valueOf(d.getDeptId())));
            this.delete(deptIdList);
        }
    }
}
