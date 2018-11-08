package com.mingrn.common.global.annotation;

import java.lang.annotation.*;

/**
 * 参数检查注解,用于开启 {@link ParamsIsNotNull} 注解功能,作用于方法
 *
 * @author MinGRn <br > MinGRn97@gmail.com
 * @date 08/11/2018 09:22
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Checked {
}
