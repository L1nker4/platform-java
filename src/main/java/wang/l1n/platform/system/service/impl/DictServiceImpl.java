package wang.l1n.platform.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.utils.SortUtil;
import wang.l1n.platform.system.dao.DictMapper;
import wang.l1n.platform.system.entity.Dict;
import wang.l1n.platform.system.service.DictService;

import java.util.Arrays;
import java.util.List;


/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/20 19:12
 * @description：
 */
@Slf4j
@Service("dictService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public IPage<Dict> findDicts(QueryRequest request, Dict dict) {
        try {
            LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();

            if (StringUtils.isNotBlank(dict.getKey())) {
                queryWrapper.eq(Dict::getKey, dict.getKey());
            }
            if (StringUtils.isNotBlank(dict.getValue())) {
                queryWrapper.eq(Dict::getValue, dict.getValue());
            }
            if (StringUtils.isNotBlank(dict.getTableName())) {
                queryWrapper.eq(Dict::getTableName, dict.getTableName());
            }
            if (StringUtils.isNotBlank(dict.getFieldName())) {
                queryWrapper.eq(Dict::getFieldName, dict.getFieldName());
            }

            Page<Dict> page = new Page<>();
            SortUtil.handlePageSort(request, page, true);
            return this.page(page, queryWrapper);
        } catch (Exception e) {
            log.error("获取字典信息失败", e);
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createDict(Dict dict) {
        this.save(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDict(Dict dict) {
        this.baseMapper.updateById(dict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDicts(String[] dictIds) {
        List<String> list = Arrays.asList(dictIds);
        this.baseMapper.deleteBatchIds(list);
    }
}
