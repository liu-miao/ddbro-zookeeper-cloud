package com.ddbro.mybatisPlus.config;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddbro.core.util.PageRequest;
import com.ddbro.core.util.PageResponse;
import com.github.pagehelper.PageInfo;

/**
 * 分页查询返回对象
 *
 * @author miaol
 * @since 2020-04-14 16:49
 */

public class PageUtil {
    /**
     * 包装mybatis-plus分页对象
     *
     * @param page mbp分页对象
     * @param <T>  泛型
     * @return 结果
     */
    public static <T> PageResponse<T> of(IPage<T> page) {
        return new PageResponse<>(page.getRecords(), page.getPages(), page.getCurrent(), page.getTotal(), page.getSize());
    }

    /**
     * 包装pageHelper结果
     *
     * @param page pageHelper对象
     * @param <T>  泛型
     * @return 结果
     */
    public static <T> PageResponse<T> of(PageInfo<T> page) {
        return new PageResponse<>(page.getList(), page.getPages(), page.getPageNum(), page.getTotal(), page.getSize());
    }

    /**
     * 包装分页请求
     *
     * @param request 请求
     * @param <R>     分页请求
     * @param <T>     实体
     * @return 分页
     */
    public static <R extends PageRequest, T> Page<T> of(R request) {
        return new Page<>(request.getPageNo(), request.getPageSize());
    }

    //如果需要spring-data，在这里加入即可

}
