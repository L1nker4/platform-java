package wang.l1n.platform.platform.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.common.entity.CommonResult;
import wang.l1n.platform.common.entity.QueryRequest;
import wang.l1n.platform.platform.pms.entity.Product;

/**
 * <p>
 * 商品管理-商品表 服务类
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-28
 */
public interface IProductService extends IService<Product> {

    CommonResult getList(QueryRequest request);
}
