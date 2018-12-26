package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author Liangjiawei
 * @date 2018/4/19 16:01
 */
@Table(name = "edoc_attach")
public class EdocAttach extends BaseDomain{
    /**
     * 对应的影像任务ID
     */
    @Column(name = "edoc_header_id")
    private Long edocHeaderId;

    /**
     * 附件名称
     */
    @Column(name = "attach_name")
    private String attachName;

    /**
     * 附件地址
     */
    @Column(name = "attach_url")
    private String attachUrl;

    /**
     * 附件路径
     */
    @Column(name = "attach_root_path")
    private String attachRootPath;

    /**
     * 附件格式
     */
    @Column(name = "attach_format")
    private String attachFormat;

    public Long getEdocHeaderId() { return edocHeaderId; }

    public void setEdocHeaderId(Long edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String getAttachRootPath() {
        return attachRootPath;
    }

    public void setAttachRootPath(String attachRootPath) {
        this.attachRootPath = attachRootPath;
    }

    public String getAttachFormat() {
        return attachFormat;
    }

    public void setAttachFormat(String attachFormat) {
        this.attachFormat = attachFormat;
    }
}
