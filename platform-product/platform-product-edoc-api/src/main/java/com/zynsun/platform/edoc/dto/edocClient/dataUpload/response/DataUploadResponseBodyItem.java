package com.zynsun.platform.edoc.dto.edocClient.dataUpload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/04 10:51
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadResponseBodyItem implements Serializable {

    /**
     * 本地唯一标识
     */
    @XmlElement(name = "GUID")
    @JsonProperty(value = "GUID")
    private String guid;

    /**
     * 服务器端标识
     */
    @XmlElement(name = "SVR_GUID")
    @JsonProperty(value = "SVR_GUID")
    private String svrGuid;

    /**
     * 上传结果
     */
    @XmlElement(name = "RESULT")
    @JsonProperty(value = "RESULT")
    private String result;

    /**
     * 返回信息
     */
    @XmlElement(name = "MESSAGE")
    @JsonProperty(value = "MESSAGE")
    private String message;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSvrGuid() {
        return svrGuid;
    }

    public void setSvrGuid(String svrGuid) {
        this.svrGuid = svrGuid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
