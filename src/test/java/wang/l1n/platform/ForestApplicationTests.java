package wang.l1n.platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import wang.l1n.platform.system.entity.Dept;
import wang.l1n.platform.system.entity.Role;
import wang.l1n.platform.system.entity.User;
import wang.l1n.platform.system.service.DeptService;
import wang.l1n.platform.system.service.RoleService;
import wang.l1n.platform.system.service.UserService;

import java.util.Date;

@SpringBootTest
class ForestApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("hello", "world");
    }

    @Test
    void deptAddTest() {
        Dept dept = new Dept();
        dept.setDeptName("开发部");
        dept.setSort(1D);
        deptService.createDept(dept);
    }


    @Test
    void roleAddTest() {
        Role role = new Role();
        role.setRoleName("超级管理员");
        role.setRemark("超级管理员");
        role.setCreateTime(new Date());
        roleService.save(role);
    }

    @Test
    void userAddTest() throws Exception {
        User user =new User();
        user.setUsername("admin");
        user.setSex("1");
        user.setDeptId(1218797128664621057L);
        user.setDeptName("开发部");
        user.setEmail("927022262@qq.com");
        user.setMobile("13912603149");
        user.setStatus("1");
        user.setRoleId("1218804579426324481");
        userService.createUser(user);
    }

}
