package com.tao.annotation;

import com.tao.common.DataFormatter;
import com.tao.common.DataFormatter.DefaultFormatter;

import java.lang.annotation.*;

/**
 * Created by huangtao8 on 2019/1/10.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Column {
    String name() default "";

    Class<? extends DataFormatter> formatter() default DefaultFormatter.class;
}
