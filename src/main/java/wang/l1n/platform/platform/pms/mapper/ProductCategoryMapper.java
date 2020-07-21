package wang.l1n.platform.platform.pms.mapper;

import wang.l1n.platform.platform.pms.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品管理-分类表 Mapper 接口
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {

    /**
     * 根据parentId，返回子分类的id
     * @param parentId      父类Id
     * @return              子类Id列表
     */
    List<Long> getChildId(Long parentId);

}
