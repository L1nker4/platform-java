package wang.l1n.platform.platform.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品管理-品牌表

 * </p>
 *
 * @author L1nker4
 * @since 2020-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_brand")
@ApiModel(value="Brand对象", description="商品管理-品牌表")
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "首字母")
    @TableField("first_letter")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "是否为品牌制造商：0->不是；1->是")
    @TableField("factory_status")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否显示")
    @TableField("show_status")
    private Integer showStatus;

    @ApiModelProperty(value = "产品数量")
    @TableField("product_count")
    private Integer productCount;

    @ApiModelProperty(value = "产品评论数量")
    @TableField("product_comment_count")
    private Integer productCommentCount;

    @ApiModelProperty(value = "品牌logo")
    @TableField("logo")
    private String logo;


}
