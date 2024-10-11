package com.ddbro.core.util;


import lombok.Setter;

import java.io.Serializable;

/**
 * 分页查询基础请求类
 *
 * @author miaol
 * @date 2020-04-11 10:44
 */
@Setter
public class PageRequest implements Serializable {

    /**
     * 默认页码，第一页
     */

    private static final int DEFAULT_PAGE_NO = 1;
    /**
     * 默认分页大小，默认10条记录
     */

    private static final int DEFAULT_PAGE_SIZE = 10;
    /**
     * 页码
     */

    protected Integer pageNo = DEFAULT_PAGE_NO;
    /**
     * 分页大小
     */
    protected Integer pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 页码
     */
    public Integer getPageNo() {
        return this.pageNo == null ? DEFAULT_PAGE_NO : this.pageNo;
    }


    /**
     * 分页大小
     */
    public Integer getPageSize() {
        return this.pageSize == null ? DEFAULT_PAGE_SIZE : this.pageSize;
    }

}
