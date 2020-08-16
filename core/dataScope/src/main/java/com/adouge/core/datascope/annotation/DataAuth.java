package com.adouge.core.datascope.annotation;

import com.adouge.core.datascope.enums.DataScopeEnum;

import java.lang.annotation.*;

/**
 * @author : Vinson
 * @date : 2020/8/13 9:56 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DataAuth {
    String code() default "";

    String column() default "created_dept";

    DataScopeEnum type() default DataScopeEnum.ALL;

    String field() default "*";

    String value() default "";
}
