package com.adouge.core.launch.annotation;

import com.adouge.core.launch.constant.AppConstant;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

/**
 * @author : Vinson
 * @date : 2020/5/26 10:35 下午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootApplication(scanBasePackages = {AppConstant.BASE_PACKAGES,"cn.hutool.extra.spring"})
public @interface AdougeSpringBootApplication {
}
