package com.zynsun.platform.edoc.dto.edocClient.dataUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端上传数据请求参数
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadRequestBody implements Serializable {

    /**
     *用户代码
     */
    @XmlElement(name = "USER_CODE")
    @JsonProperty(value = "USER_CODE")
    private String userCode;

    /**
     * 单证条目集合
     */
    @XmlElement(name = "DATAITEM")
    @JsonProperty(value = "DATAITEM")
    private DataUploadRequestBodyDataItem dataItem;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public DataUploadRequestBodyDataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DataUploadRequestBodyDataItem dataItem) {
        this.dataItem = dataItem;
    }
}
