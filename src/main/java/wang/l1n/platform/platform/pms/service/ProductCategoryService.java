package wang.l1n.platform.platform.pms.service;

import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.platform.pms.entity.request.AddProductCategoryRequest;
import wang.l1n.platform.platform.pms.entity.request.DeleteProductCategoryRequest;

/**
 * <p>
 * 商品管理-分类表 服务类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    /**
     * 删除
     * @param request
     * @return
     */
    CommonResult delete(DeleteProductCategoryRequest request);

    /**
     * 获取分类列表
     * @param request
     * @return
     */
    CommonResult getList(QueryRequest request);
}
