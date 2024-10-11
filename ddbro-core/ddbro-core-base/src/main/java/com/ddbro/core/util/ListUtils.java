package com.ddbro.core.util;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 复制列表工具
 *
 * @author liumiao
 * @date 2021/09/09
 */
public class ListUtils extends BeanUtils {

    public static <S, T> List<T> copyListByStream(List<S> sources, Supplier<T> target) {
        return sources.stream().map(i -> {
            T t = target.get();
            copyProperties(i, t);
            return t;
        }).collect(Collectors.toList());
    }
}
