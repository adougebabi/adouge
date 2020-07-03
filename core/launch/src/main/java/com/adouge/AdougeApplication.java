package com.adouge;

import com.adouge.core.launch.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

import java.util.Properties;

/**
 * @author : Vinson
 * @date : 2020/5/16 4:17 下午
 */
public class AdougeApplication {

    public static ConfigurableApplicationContext run(String appName, Class<?> source, String... args) {
        Assert.hasText(appName, "[appName]服务名不能为空");
        Class<?>[] objects = new Class[2];
        objects[0] = AdougeApplication.class;
        objects[1] = source;

        /* 默认设置 */
        Properties props = System.getProperties();
        props.setProperty("adouge.env", AppConstant.DEV_CODE);
        return SpringApplication.run(objects, args);
    }
}
