package com.ddbro.mybatisPlus.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ddbro.core.util.PageRequest;

/**
 * 查询分页对象
 *
 * @author liumiao
 * @date 2022/07/24
 */
public class QueryPage<T extends PageRequest> extends Page<T> {

    private static final Long DEFAULT_PAGE_NO = 1L;
    private static final Long DEFAULT_PAGE_SIZE = 10L;

    public QueryPage() {
        super(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
    }

    public QueryPage(PageRequest request) {
        super(request.getPageNo(), request.getPageSize());
    }

    public Long getPageNo() {
        return this.getCurrent();
    }

    public void setPageNo(String pageNo) {
        if (StringUtils.isNotBlank(pageNo)) {
            this.setCurrent(Integer.parseInt(pageNo));
        }
    }

    public Long getPageSize() {
        return this.getSize();
    }

    public void setPageSize(String pageSize) {
        if (StringUtils.isNotBlank(pageSize)) {
            this.setSize(Integer.parseInt(pageSize));
        }
    }
}
