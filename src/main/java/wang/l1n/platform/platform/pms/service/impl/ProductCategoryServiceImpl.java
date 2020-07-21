package wang.l1n.platform.platform.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.common.entity.constant.MessageConstant;
import wang.l1n.platform.common.utils.SortUtil;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import wang.l1n.platform.platform.pms.entity.request.DeleteProductCategoryRequest;
import wang.l1n.platform.platform.pms.mapper.ProductCategoryMapper;
import wang.l1n.platform.platform.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.l1n.platform.system.entity.Dict;

import java.util.List;

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
    public CommonResult delete(DeleteProductCategoryRequest request) {
        //父级分类级联删除
        if (request.getParentId() == 0){
            List<Long> ids = productCategoryMapper.getChildId(request.getParentId());
            ids.forEach((id) -> {
                productCategoryMapper.deleteById(id);
            });
            productCategoryMapper.deleteById(request.getId());
        }else {
            //只需删除自己
            productCategoryMapper.deleteById(request.getId());
        }
        return new CommonResult().success(MessageConstant.DELETE_SUCCESS_MESSAGE);
    }

    @Override
    public CommonResult getList(QueryRequest request) {
        Page<ProductCategory> page = new Page<>();
        SortUtil.handlePageSort(request, page, true);
        IPage<ProductCategory> categoryData = this.page(page, null);
        return new CommonResult().success(categoryData);
    }
}
