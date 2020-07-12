package wang.l1n.platform.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 12:13
 * @description： 登录日志实体类
 */
@TableName("sys_login_log")
@Data
public class LoginLog {

    @TableId(value = "id",type = IdType.ID_WORKER)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 用户 ID
     */
    private String username;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录地点
     */
    private String location;

    /**
     * 登录IP
     */
    private String ip;
}
