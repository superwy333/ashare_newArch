package com.zynsun.openplatform.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Page对象，返回的页面上需要的对象
 */
public class PageInfo<T> extends com.github.pagehelper.PageInfo<T> {

    /**
     * 当前页
     * @return
     */
    @JsonProperty(value = "page")
    public int getPageNum() {
        return super.getPageNum();
    }

    /**
     * 每页大小
     * @return
     */
    @JsonProperty(value = "pageSize")
    public int getPageSize() {
        return super.getPageSize();
    }

    /**
     * 总页数
     * @return
     */
    @JsonProperty(value = "total")
    public int getPages() {
        return super.getPages();
    }

    /**
     * 总记录数
     * @return
     */
    @JsonProperty(value = "records")
    public long getTotal() {
        return super.getTotal();
    }

    /**
     * 记录行
     * @return
     */
    @JsonProperty(value = "rows")
    public List<T> getList() {
        return super.getList();
    }
}
