package com.zynsun.openplatform.dto;

/**
 * 分页DTO
 * 
 * @ClassName: PageDTO
 * @author gongdaowen
 * @date 2017年03月31日
 * 
 */
public class PageDTO extends BaseDTO {

    private Integer page = 1; // 当前页
    private Integer rows = 10; // 每页大小

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return rows;
    }

    public void setPageSize(Integer pageSize) {
        this.rows = pageSize;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

}
