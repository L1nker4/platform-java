package wang.l1n.platform.platform.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.Tree;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.common.utils.TreeUtil;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import wang.l1n.platform.platform.pms.entity.request.DeleteProductCategoryRequest;
import wang.l1n.platform.platform.pms.mapper.ProductCategoryMapper;
import wang.l1n.platform.platform.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 商品管理-分类表 服务实现类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {


    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public CommonResult deleteCategory(String ids) {
        String[] split = ids.split(StringPool.COMMA);
        delete(Arrays.asList(split));
        return new CommonResult().success(MessageConstant.DELETE_SUCCESS_MESSAGE);
    }

    private void delete(List<String> ids) {
        removeByIds(ids);
        LambdaQueryWrapper<ProductCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ProductCategory::getParentId, ids);
        List<ProductCategory> categories = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(categories)){
            List<String> list = new ArrayList<>();
            categories.forEach((category -> list.add(String.valueOf(category.getId()))));
            delete(list);
        }
    }

    @Override
    public CommonResult getList(QueryRequest request) {
        Map<String, Object> result = new HashMap<>();
        try {
            List<ProductCategory> list = this.list();
            List<Tree<ProductCategory>> trees = new ArrayList<>();
            buildTrees(trees, list);
            Tree<ProductCategory> deptTree = TreeUtil.build(trees);
            result.put("rows", deptTree);
            result.put("total", list.size());
        } catch (Exception e) {
            log.error("获取商品分类列表失败", e);
            result.put("rows", null);
            result.put("total", 0);
        }
        return new CommonResult().success(result);
    }

    private void buildTrees(List<Tree<ProductCategory>> trees, List<ProductCategory> categories) {
        categories.forEach(category -> {
            Tree<ProductCategory> tree = new Tree<>();
            tree.setId(category.getId().toString());
            tree.setName(category.getName());
            tree.setLevel(category.getLevel());
            tree.setTitle(category.getName());
            tree.setKey(tree.getId());
            tree.setProductCount(category.getProductCount());
            tree.setProductUnit(category.getProductUnit());
            tree.setShowStatus(category.getShowStatus());
            tree.setNavStatus(category.getNavStatus());
            tree.setLogo(category.getLogo());
            tree.setKeywords(category.getKeywords());
            tree.setDescription(category.getDescription());
            tree.setParentId(category.getParentId().toString());
            tree.setCreateTime(category.getCreateTime());
            tree.setUpdateTime(category.getUpdateTime());
            trees.add(tree);
        });
    }
}
