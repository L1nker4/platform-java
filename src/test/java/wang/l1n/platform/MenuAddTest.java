package wang.l1n.platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.l1n.platform.common.properties.ForestProperties;
import wang.l1n.platform.system.entity.Menu;
import wang.l1n.platform.system.service.MenuService;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/1/20 19:30
 * @description：
 */

@SpringBootTest
public class MenuAddTest {

    @Autowired
    private MenuService menuService;


    @Autowired
    ForestProperties forestProperties;


    @Test
    public void test01() {
        Menu menu = new Menu();
        menu.setMenuName("导出Excel");
        menu.setParentId(1219223716672602114L);
        menu.setPerms("user:reset");
        menu.setIcon("");
        menu.setType("1");

        menuService.createMenu(menu);
    }

    @Test
    public void test02() {
        System.out.println(forestProperties.getShiro().getAnonUrl());
    }
}
