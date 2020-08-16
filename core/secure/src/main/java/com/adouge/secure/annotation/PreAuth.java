package com.adouge.secure.annotation;

import java.lang.annotation.*;

/**
 * @author : Vinson
 * @date : 2020/8/13 11:19 下午
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PreAuth {
    String value();
}
