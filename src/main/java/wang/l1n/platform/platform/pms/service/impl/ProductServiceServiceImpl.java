package wang.l1n.platform.platform.pms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.platform.pms.entity.ProductService;
import wang.l1n.platform.platform.pms.mapper.ProductServiceMapper;
import wang.l1n.platform.platform.pms.service.ProductServiceService;

/**
 * <p>
 * 商品管理-商品服务表 服务实现类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-29
 */
@Service
public class ProductServiceServiceImpl extends ServiceImpl<ProductServiceMapper, ProductService> implements ProductServiceService {

    @Override
    public IPage<ProductService> getList(QueryRequest request) {
        Page<ProductService> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.selectPage(page, new QueryWrapper<>());
    }
}
