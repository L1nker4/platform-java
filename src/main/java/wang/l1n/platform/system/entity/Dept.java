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
 * @date       ： 创建于  2020/1/18 12:11
 * @description： 部门实体类
 */
@Data
@TableName("sys_dept")
@Excel("部门信息表")
public class Dept implements Serializable {

    private static final long serialVersionUID = -7790334862410409053L;

    @TableId(value = "DEPT_ID", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long deptId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @NotBlank(message = "{required}")
    @Size(max = 20, message = "{noMoreThan}")
    @ExcelField(value = "部门名称")
    private String deptName;

    private Double sort;

    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date updateTime;

    private transient String createTimeFrom;

    private transient String createTimeTo;

}