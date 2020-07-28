package wang.l1n.platform.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.l1n.platform.common.authentication.JWTUtil;
import wang.l1n.platform.common.utils.ForestUtil;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/21 12:54
 * @description： Mybatis Plus 数据填充
 */
@Component
public class DBMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
