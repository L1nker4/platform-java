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
 * @description： 菜单信息实体类
 */
@Data
@TableName("sys_menu")
@Excel("菜单信息表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 7187628714679791771L;

    public static final String TYPE_MENU = "0";

    public static final String TYPE_BUTTON = "1";

    @TableId(value = "menu_id", type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    @NotBlank(message = "{required}")
    @Size(max = 10, message = "{noMoreThan}")
    @ExcelField(value = "名称")
    private String menuName;

    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "地址")
    private String path;

    @Size(max = 100, message = "{noMoreThan}")
    @ExcelField(value = "对应Vue组件")
    private String component;

    @Size(max = 50, message = "{noMoreThan}")
    @ExcelField(value = "权限")
    private String perms;

    @ExcelField(value = "图标")
    private String icon;

    @NotBlank(message = "{required}")
    @ExcelField(value = "类型", writeConverterExp = "0=按钮,1=菜单")
    private String type;

    private Double sort;

    @ExcelField(value = "创建时间", writeConverter = TimeConverter.class)
    private Date createTime;

    @ExcelField(value = "修改时间", writeConverter = TimeConverter.class)
    private Date updateTime;

    private transient String createTimeFrom;
    private transient String createTimeTo;

}