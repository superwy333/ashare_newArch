package com.zynsun.openplatform.domain;

import javax.persistence.Transient;

/**
 * @author gongdaowen
 * @ClassName: PageModel
 * @Description: 分页基础类
 * @date 2017年03月31日
 */
public class PageModel extends BaseDomain {

    @Transient
    private Integer page; // 当前页
    @Transient
    private Integer pageSize; // 每页大小

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void fetchAll(){
        this.page = 1;
        this.pageSize = -1;
    }

}
