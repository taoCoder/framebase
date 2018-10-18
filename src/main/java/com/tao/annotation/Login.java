package com.tao.annotation;

import java.lang.annotation.*;

/**
 * Created by huangtao8 on 2018/10/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Login {
}
