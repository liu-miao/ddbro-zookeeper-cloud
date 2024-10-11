package com.ddbro.file.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 香蕉
 *
 * @author liumiao
 * @date 2022/10/07
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Banana {
    /**
     * 名字
     * 表头名称
     *
     * @return {@link String}
     */
    String name();

    /**
     * 宽度
     * 单元格的宽度 （单位1~256个字符宽度）
     * 默认0:表示自动宽度
     *
     * @return int
     */
    int width() default 0;

    /**
     * 合并
     * 是否合并单元格
     * 默认不合并
     *
     * @return boolean
     */
    boolean merge() default false;
}
