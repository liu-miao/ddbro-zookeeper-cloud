package com.ddbro.mybatisPlus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ddbro.core.util.ID;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * mybatis + 自动填充
 *
 * @author liumiao
 * @date 2022/06/14
 */
@Component
//@ConditionalOnProperty(prefix = "spring.datasource", name = "url")
public class MyBatisMetaHandler implements MetaObjectHandler {


    /**
     * 插入填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
        this.setFieldValByName("gmtCreated", LocalDateTime.now(), metaObject);
        this.setFieldValByName("id", ID.getIdStr(), metaObject);
    }

    /**
     * 更新填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", LocalDateTime.now(), metaObject);
    }

}
