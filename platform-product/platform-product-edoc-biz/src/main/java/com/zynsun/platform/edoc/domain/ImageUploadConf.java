package com.zynsun.platform.edoc.domain;

import com.zynsun.openplatform.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "image_upload_conf")
public class ImageUploadConf extends BaseDomain {

    @Column(name = "upload_type")
    private String uploadType;

    public String getFtpRootPath() {
        return ftpRootPath;
    }

    public void setFtpRootPath(String ftpRootPath) {
        this.ftpRootPath = ftpRootPath;
    }

    @Column(name = "ftp_root_path")
    private String ftpRootPath;

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    @Column(name = "relative_path")
    private String relativePath;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    @Column(name = "absolute_path")
    private String absolutePath;

    @Column(name = "ftp_username")
    private String ftpUsername;

    @Column(name = "ftp_pwd")
    private String ftpPwd;

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    @Column(name = "ftp_url")
    private String ftpUrl;

    @Column(name = "ftp_port")
    private String ftpPort;

    /**
     * 备注
     */
    private String remarks;


    /**
     * @return ftp_username
     */
    public String getFtpUsername() {
        return ftpUsername;
    }

    /**
     * @param ftpUsername
     */
    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    /**
     * @return ftp_pwd
     */
    public String getFtpPwd() {
        return ftpPwd;
    }

    /**
     * @param ftpPwd
     */
    public void setFtpPwd(String ftpPwd) {
        this.ftpPwd = ftpPwd;
    }



    /**
     * @return ftp_port
     */
    public String getFtpPort() {
        return ftpPort;
    }

    /**
     * @param ftpPort
     */
    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }

    /**
     * 获取备注
     *
     * @return remarks - 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUploadType() {
        return uploadType;
    }

    public void setUploadType(String uploadType) {
        this.uploadType = uploadType;
    }

}