package com.zynsun.openplatform.dto;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 基础Vo
 * 
 * @ClassName: BaseVo
 * @author yechuan
 * @date 2016年5月22日 下午9:57:59
 * 
 */
public class BaseResultDTO implements Serializable {

	private static final long serialVersionUID = -1524194532303522645L;

    private Long id;

    private String createBy;

    private String lastModifyBy;

    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd")
    private Date lastModifyTime;

    /** 版本号（乐观锁）  严禁设置默认值 */
    private Long version;

    /** 逻辑删除标记 默认0:未删除 */
    private Integer deleted ;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModifyBy() {
        return this.lastModifyBy;
    }

    public void setLastModifyBy(String lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return this.lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
