package com.zynsun.platform.edoc.dto.edocClient.endUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端结束上次请求任务信息
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class EndUploadRequestBodyItem implements Serializable {

    /**
     * 任务ID: beforeUpload返回的BILL_HEADER_ID
     */
    @XmlElement(name = "TASK_ID")
    @JsonProperty(value = "TASK_ID")
    private String taskId;

    /**
     * 影像张数
     */
    @XmlElement(name = "PAGE_NUM")
    @JsonProperty(value = "PAGE_NUM")
    private String pageNum;

    /**
     * 预留字段1
     */
    @XmlElement(name = "EXT_FIELD1")
    @JsonProperty(value = "EXT_FIELD1")
    private String extField1;

    /**
     * 预留字段2
     */
    @XmlElement(name = "EXT_FIELD2")
    @JsonProperty(value = "EXT_FIELD2")
    private String extField2;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExtField1() {
        return extField1;
    }

    public void setExtField1(String extField1) {
        this.extField1 = extField1;
    }

    public String getExtField2() {
        return extField2;
    }

    public void setExtField2(String extField2) {
        this.extField2 = extField2;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
