package wang.l1n.platform.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:14
 * @description： 权限角色关系
 */
@TableName("sys_role_menu")
@Data
public class RoleMenu implements Serializable {
	
	private static final long serialVersionUID = -7573904024872252113L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long menuId;
}