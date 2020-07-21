package wang.l1n.platform.platform.pms.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/21 9:36
 * @description： 删除商品分类 请求类
 */
@Data
@ApiModel(value="Delete ProductCategory Request", description="商品管理-分类表")
public class DeleteProductCategoryRequest {

    @ApiModelProperty(value = "商品分类id")
    @NotNull(message = "{required}")
    private Long id;

    @ApiModelProperty(value = "上级分类的编号：0表示一级分类")
    private Long parentId;
}
