package wang.l1n.platform.platform.pms.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/29 20:21
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateProductServiceRequest extends AddProductServiceRequest{

    @ApiModelProperty(value = "id")
    @NotNull(message = "{required}")
    private String id;
}
