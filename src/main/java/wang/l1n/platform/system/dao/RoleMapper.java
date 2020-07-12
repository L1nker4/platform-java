package wang.l1n.platform.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wang.l1n.platform.system.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
	
	List<Role> findUserRole(String userName);
	
}