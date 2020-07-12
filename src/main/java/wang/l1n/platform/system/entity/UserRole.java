package wang.l1n.platform.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:23
 * @description： 用户角色关系
 */
@TableName("sys_user_role")
@Data
public class UserRole implements Serializable{
	
	private static final long serialVersionUID = -3166012934498268403L;

    @JsonSerialize(using = ToStringSerializer.class)
	private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
	private Long roleId;

}