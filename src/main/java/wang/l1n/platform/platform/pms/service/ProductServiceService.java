package wang.l1n.platform.platform.pms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.platform.pms.entity.ProductService;

/**
 * <p>
 * 商品管理-商品服务表 服务类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-29
 */
public interface ProductServiceService extends IService<ProductService> {

    /**
     * 分页
     * @param request
     * @return
     */
    IPage<ProductService> getList(QueryRequest request);
}
