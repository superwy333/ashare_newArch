package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.PageDTO;

/**
 * @author Liangjiawei
 * @date 2018/4/19 15:44
 */
public class EdocAttachDTO extends PageDTO {
    /**
     * 对应的影像任务ID
     */
    private Long edocHeaderId;

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
