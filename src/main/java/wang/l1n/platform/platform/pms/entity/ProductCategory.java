package wang.l1n.platform.platform.pms.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import wang.l1n.platform.platform.pms.entity.enums.ShowStatusEnums;

/**
 * <p>
 * 商品管理-分类表
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @TableField("level")
    private Integer level;

    @TableField("product_count")
    private Integer productCount;

    @TableField("product_unit")
    private String productUnit;

    @TableField("nav_status")
    private ShowStatusEnums navStatus;

    @TableField("show_status")
    private ShowStatusEnums showStatus;

    @TableField("sort")
    private Integer sort;

    @TableField("logo")
    private String logo;

    @TableField("keywords")
    private String keywords;

    @TableField("description")
    private String description;

}
