package wang.l1n.platform.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;
import wang.l1n.platform.common.converter.TimeConverter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:15
 * @description： 角色实体类
 */
@Data
@TableName("sys_role")
@Excel("角色信息表")
public class Role implements Serializable {

    private static final long serialVersionUID = -1714476694755654924L;

    @TableId(value = "role_id", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "角色名称")
    private String roleName;

    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "角色描述")
    private String remark;

    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date updateTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;
    private transient String menuId;

}