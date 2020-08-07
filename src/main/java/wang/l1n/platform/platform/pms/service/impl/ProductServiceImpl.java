package wang.l1n.platform.platform.pms.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.platform.pms.entity.Product;
import wang.l1n.platform.platform.pms.mapper.ProductMapper;
import wang.l1n.platform.platform.pms.service.IProductService;

/**
 * <p>
 * 商品管理-商品表 服务实现类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-28
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public CommonResult getList(QueryRequest request) {
        IPage<Product> page = new Page<>(request.getPageNum(), request.getPageSize());
        IPage<Product> list = productMapper.selectPage(page, null);
        return new CommonResult().success(list);
    }
}
