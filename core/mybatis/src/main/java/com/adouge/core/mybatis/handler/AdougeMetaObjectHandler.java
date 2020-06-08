package com.adouge.core.mybatis.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author : Vinson
 * @date : 2020/6/8 11:22 上午
 */
@Slf4j
@Component
public class AdougeMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start insert fill ....");
        }
//        TODO
        this.fillStrategy(metaObject, "createdTime",  LocalDateTime.now());
        this.fillStrategy(metaObject, "createdBy", 1L);
        this.fillStrategy(metaObject, "status",  0);
        this.fillStrategy(metaObject, "isDeleted",  0);
        this.fillStrategy(metaObject, "version",  1L);
        this.fillStrategy(metaObject, "updatedTime",  LocalDateTime.now());
        this.fillStrategy(metaObject, "updatedBy", 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //        TODO
        if (log.isDebugEnabled()) {
            log.debug("start update fill ....");
        }
        this.fillStrategy(metaObject, "updatedTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "updatedBy", 1L);
    }
}
