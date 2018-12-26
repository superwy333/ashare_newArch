package com.zynsun.platform.edoc.dto;

/**
 * Created by Jary on 2018/11/26/026.
 */
public class BeanDTO {

    private String taskId;

    private String imageId;

    private String edocNo;

    private byte[] bytes;

    private String fileFormat;

    private String edocImageType;

    private String enclosureName;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getEdocNo() {
        return edocNo;
    }

    public void setEdocNo(String edocNo) {
        this.edocNo = edocNo;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getEdocImageType() {
        return edocImageType;
    }

    public void setEdocImageType(String edocImageType) {
        this.edocImageType = edocImageType;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }
}
