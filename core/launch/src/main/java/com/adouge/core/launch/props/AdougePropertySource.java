package com.adouge.core.launch.props;

import java.lang.annotation.*;

/**
 * @author : Vinson
 * @date : 2020/6/12 10:20 上午
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdougePropertySource {
    String value();

    boolean loadActiveProfile() default true;

    int order() default Integer.MAX_VALUE;
}
