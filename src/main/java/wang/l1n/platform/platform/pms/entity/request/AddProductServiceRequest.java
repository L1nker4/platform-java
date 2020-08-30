package wang.l1n.platform.platform.pms.entity.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.swagger.common.SpringVersionCapability;

import javax.validation.constraints.NotNull;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/29 20:20
 * @description：
 */

@Data
public class AddProductServiceRequest {

    @ApiModelProperty(value = "服务名称")
    @NotNull(message = "{required}")
    private String name;

    @ApiModelProperty(value = "描述")
    @NotNull(message = "{required}")
    private String description;

    @ApiModelProperty(value = "Logo")
    @NotNull(message = "{required}")
    private String logo;
}
