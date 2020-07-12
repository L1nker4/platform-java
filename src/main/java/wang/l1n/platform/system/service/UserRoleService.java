package wang.l1n.platform.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import wang.l1n.platform.system.entity.UserRole;

import java.util.List;

public interface UserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String[] roleIds);

	void deleteUserRolesByUserId(String[] userIds);

	List<String> findUserIdsByRoleId(String[] roleIds);
}
