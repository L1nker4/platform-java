package wang.l1n.platform.platform.pms.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.platform.pms.entity.ProductCategory;
import wang.l1n.platform.platform.pms.entity.request.AddProductCategoryRequest;
import wang.l1n.platform.platform.pms.mapper.ProductCategoryMapper;
import wang.l1n.platform.platform.pms.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
