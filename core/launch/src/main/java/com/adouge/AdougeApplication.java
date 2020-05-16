package com.adouge;

import com.adouge.core.launch.constant.AppConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
