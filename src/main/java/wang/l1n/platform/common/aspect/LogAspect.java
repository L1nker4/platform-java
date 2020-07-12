package wang.l1n.platform.common.aspect;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.l1n.platform.common.authentication.JWTUtil;
import wang.l1n.platform.common.properties.ForestProperties;
import wang.l1n.platform.common.utils.HttpContextUtil;
import wang.l1n.platform.common.utils.IPUtil;
import wang.l1n.platform.system.entity.SysLog;
import wang.l1n.platform.system.service.LogService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/4 14:22
 * @description： 日志切面类
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ForestProperties forestProperties;

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(wang.l1n.platform.common.annotation.Log)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long beginTime = System.currentTimeMillis();
        // 执行方法
        result = point.proceed();
        // 获取 request
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        // 设置 IP 地址
        String ip = IPUtil.getIpAddr(request);
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        if (forestProperties.isOpenAopLog()) {
            // 保存日志
            String token = (String) SecurityUtils.getSubject().getPrincipal();
            String username = "";
            if (StringUtils.isNotBlank(token)) {
                username = JWTUtil.getUsername(token);
            }

            SysLog log = new SysLog();
            log.setUsername(username);
            log.setIp(ip);
            log.setTime(time);
            logService.saveLog(point, log);
        }
        return result;
    }
}
