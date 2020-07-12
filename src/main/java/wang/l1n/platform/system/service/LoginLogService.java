package wang.l1n.platform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.system.entity.LoginLog;

public interface LoginLogService extends IService<LoginLog> {

    void saveLoginLog(LoginLog loginLog);
}
