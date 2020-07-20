package wang.l1n.platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.l1n.platform.common.properties.ForestProperties;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/20 11:09
 * @description：
 */
@SpringBootTest
public class PropertiesTest {

    @Autowired
    private ForestProperties properties;
    @Test
    public void test01(){
        System.out.println(properties.getOss().getAccessKeyId());
    }
}
