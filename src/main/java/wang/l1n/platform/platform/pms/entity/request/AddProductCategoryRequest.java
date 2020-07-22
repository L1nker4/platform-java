package wang.l1n.platform.platform.pms.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.l1n.platform.platform.pms.entity.enums.ShowStatusEnums;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 22:39
 * @description： 添加商品分类 请求类
 */

@Data
@ApiModel(value="Add ProductCategory Request", description="商品管理-分类表")
public class AddProductCategoryRequest {

    @ApiModelProperty(value = "上级分类的编号：0表示一级分类")
    private Long parentId;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "{required}")
    private String name;

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    private Integer level;

    @ApiModelProperty(value = "商品数量")
    @NotNull(message = "{required}")
    private Integer productCount;

    @ApiModelProperty(value = "商品单位")
    @NotBlank(message = "{required}")
    private String productUnit;

    @ApiModelProperty(value = "是否显示在导航栏：0->不显示；1->显示")
    @NotNull(message = "{required}")
    private ShowStatusEnums navStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @NotNull(message = "{required}")
    private ShowStatusEnums showStatus;

    @ApiModelProperty(value = "排序")
    @NotNull(message = "{required}")
    private Integer sort;

    @ApiModelProperty(value = "logo图片地址")
    private String logo;

    @ApiModelProperty(value = "关键字")
    @NotBlank(message = "{required}")
    private String keywords;

    @ApiModelProperty(value = "描述")
    @NotBlank(message = "{required}")
    private String description;

}
