package wang.l1n.platform.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wang.l1n.platform.common.utils.AddressUtil;
import wang.l1n.platform.common.utils.HttpContextUtil;
import wang.l1n.platform.common.utils.IPUtil;
import wang.l1n.platform.system.dao.LoginLogMapper;
import wang.l1n.platform.system.entity.LoginLog;
import wang.l1n.platform.system.service.LoginLogService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/19 10:01
 * @description： 
 */
@Service("loginLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveLoginLog(LoginLog loginLog) {
        loginLog.setLoginTime(new Date());
        HttpServletRequest request = HttpContextUtil.getHttpServletRequest();
        String ip = IPUtil.getIpAddr(request);
        loginLog.setIp(ip);
        loginLog.setLocation(AddressUtil.getCityInfo(ip));
        this.save(loginLog);
    }
}
