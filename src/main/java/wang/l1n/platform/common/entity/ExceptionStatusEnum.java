package wang.l1n.platform.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/18 11:46
 * @description：  异常状态枚举
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionStatusEnum {


    /**
     * 用户类异常 1开头
     */
    ERROR_USERNAME_OR_PASSWORD("用户名或者密码错误",101),
    ;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 错误代码
     */
    private Integer code;


}
