package wang.l1n.platform.platform.pms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import wang.l1n.platform.common.entity.common.BaseEntity;

/**
 * <p>
 * 商品管理-商品服务表
 * </p>
 *
 * @author L1nker4
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_product_service")
@Excel("商品服务表")
public class ProductService extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ExcelField(value = "ID")
    private String id;

    @TableField("name")
    @ExcelField(value = "服务名称")
    private String name;

    @TableField("logo")
    @ExcelField(value = "Logo")
    private String logo;

    @TableField("description")
    @ExcelField(value = "描述")
    private String description;

}
