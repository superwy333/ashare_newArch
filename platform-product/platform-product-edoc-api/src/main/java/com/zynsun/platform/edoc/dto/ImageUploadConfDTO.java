package com.zynsun.platform.edoc.dto;

import com.zynsun.openplatform.dto.BaseDTO;

/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 11:11 2018/1/11
 * @Modified By:
 */
public class ImageUploadConfDTO extends BaseDTO{

    private String uploadType;

    public String getFtpRootPath() {
        return ftpRootPath;
    }

    public void setFtpRootPath(String ftpRootPath) {
        this.ftpRootPath = ftpRootPath;
    }

    private String ftpRootPath;

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    private String relativePath;

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    private String absolutePath;

    private String ftpUsername;

    private String ftpPwd;

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    private String ftpUrl;

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
