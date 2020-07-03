package com.adouge.core.mybatis.handler;


import com.adouge.secure.AdougeUser;
import com.adouge.secure.utils.SecureUtil;
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
        AdougeUser user = SecureUtil.getUser();
        assert user != null;
        this.fillStrategy(metaObject, "createdTime",  LocalDateTime.now());
        this.fillStrategy(metaObject, "createdBy", user.getUserId());
        this.fillStrategy(metaObject, "status",  0);
        this.fillStrategy(metaObject, "isDeleted",  0);
        this.fillStrategy(metaObject, "version",  1L);
        this.fillStrategy(metaObject, "updatedTime",  LocalDateTime.now());
        this.fillStrategy(metaObject, "updatedBy", user.getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (log.isDebugEnabled()) {
            log.debug("start update fill ....");
        }
        AdougeUser user = SecureUtil.getUser();
        assert user != null;
        this.fillStrategy(metaObject, "updatedTime", LocalDateTime.now());
        this.fillStrategy(metaObject, "updatedBy", user.getUserId());
    }
}
