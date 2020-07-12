package wang.l1n.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author     ：L1nker4
 * @date       ： 创建于  2020/1/4 14:01
 * @description：
 */
@SpringBootApplication
@MapperScan("wang.l1n.platform.system.dao")
@EnableTransactionManagement
@EnableCaching
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }

}
