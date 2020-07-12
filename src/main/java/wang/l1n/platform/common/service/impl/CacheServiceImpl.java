package wang.l1n.platform.common.service.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.l1n.platform.common.entity.ForestConstant;
import wang.l1n.platform.common.exception.ForestException;
import wang.l1n.platform.common.service.CacheService;
import wang.l1n.platform.common.service.RedisService;
import wang.l1n.platform.system.dao.UserMapper;
import wang.l1n.platform.system.entity.Menu;
import wang.l1n.platform.system.entity.Role;
import wang.l1n.platform.system.entity.User;
import wang.l1n.platform.system.entity.UserConfig;
import wang.l1n.platform.system.service.MenuService;
import wang.l1n.platform.system.service.RoleService;
import wang.l1n.platform.system.service.UserConfigService;
import wang.l1n.platform.system.service.UserService;

import java.util.List;

@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConfigService userConfigService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void testConnect() throws Exception {
        this.redisService.exists("test");
    }

    @Override
    public User getUser(String username) throws Exception {
        String userString = this.redisService.get(ForestConstant.USER_CACHE_PREFIX + username);
        if (StringUtils.isEmpty(userString)) {
            throw new ForestException("缓存未击中");
        } else {
            return this.mapper.readValue(userString, User.class);
        }
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        String roleListString = this.redisService.get(ForestConstant.USER_ROLE_CACHE_PREFIX + username);
        if (StringUtils.isNotBlank(roleListString)) {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Role.class);
            return this.mapper.readValue(roleListString, type);
        }
        return null;
    }

    @Override
    public List<Menu> getPermissions(String username) throws Exception {
        String permissionListString = this.redisService.get(ForestConstant.USER_PERMISSION_CACHE_PREFIX + username);
        if (StringUtils.isNotBlank(permissionListString)) {
            JavaType type = mapper.getTypeFactory().constructParametricType(List.class, Menu.class);
            return this.mapper.readValue(permissionListString, type);
        }
        return null;
    }

    @Override
    public UserConfig getUserConfig(String userId) throws Exception {
        String userConfigString = this.redisService.get(ForestConstant.USER_CONFIG_CACHE_PREFIX + userId);
        if (StringUtils.isNotBlank(userConfigString)) {
            return this.mapper.readValue(userConfigString, UserConfig.class);
        }
        return null;
    }

    @Override
    public void saveUser(User user) throws Exception {
        String username = user.getUsername();
        this.deleteUser(username);
        redisService.set(ForestConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userMapper.findDetail(username);
        this.deleteUser(username);
        redisService.set(ForestConstant.USER_CACHE_PREFIX + username, mapper.writeValueAsString(user));
    }

    @Override
    public void saveRoles(String username) throws Exception {
        List<Role> roleList = this.roleService.findUserRole(username);
        if (!roleList.isEmpty()) {
            this.deleteRoles(username);
            redisService.set(ForestConstant.USER_ROLE_CACHE_PREFIX + username, mapper.writeValueAsString(roleList));
        }

    }

    @Override
    public void savePermissions(String username) throws Exception {
        List<Menu> permissionList = this.menuService.findUserPermissions(username);
        if (!permissionList.isEmpty()) {
            this.deletePermissions(username);
            redisService.set(ForestConstant.USER_PERMISSION_CACHE_PREFIX + username, mapper.writeValueAsString(permissionList));
        }
    }

    @Override
    public void saveUserConfigs(String userId) throws Exception {
        UserConfig userConfig = this.userConfigService.findByUserId(userId);
        if (userConfig != null) {
            this.deleteUserConfigs(userId);
            redisService.set(ForestConstant.USER_CONFIG_CACHE_PREFIX + userId, mapper.writeValueAsString(userConfig));
        }
    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(ForestConstant.USER_CACHE_PREFIX + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(ForestConstant.USER_ROLE_CACHE_PREFIX + username);
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisService.del(ForestConstant.USER_PERMISSION_CACHE_PREFIX + username);
    }

    @Override
    public void deleteUserConfigs(String userId) throws Exception {
        redisService.del(ForestConstant.USER_CONFIG_CACHE_PREFIX + userId);
    }
}
