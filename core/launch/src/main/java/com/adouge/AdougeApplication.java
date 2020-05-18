package com.adouge;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;
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
        return SpringApplication.run(objects, args);
    }
}
