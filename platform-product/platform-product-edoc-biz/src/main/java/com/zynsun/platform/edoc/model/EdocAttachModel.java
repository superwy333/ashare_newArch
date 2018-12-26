package com.zynsun.platform.edoc.model;

import com.zynsun.openplatform.domain.PageModel;

/**
 * @author Liangjiawei
 * @date 2018/4/19 16:21
 */
public class EdocAttachModel extends PageModel {
    /**
     * 对应的影像任务ID
     */
    private Integer edocHeaderId;

    /**
     * 附件名称
     */
    private String attachName;

    /**
     * 附件地址
     */
    private String attachUrl;

    /**
     * 附件路径
     */
    private String attachRootPath;

    /**
     * 附件格式
     */
    private String attachFormat;

    public Integer getEdocHeaderId() { return edocHeaderId; }

    public void setEdocHeaderId(Integer edocHeaderId) {
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
