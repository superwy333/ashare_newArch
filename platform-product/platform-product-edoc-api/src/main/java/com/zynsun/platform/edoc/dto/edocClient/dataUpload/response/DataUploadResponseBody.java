package com.zynsun.platform.edoc.dto.edocClient.dataUpload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 11:15
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadResponseBody implements Serializable {

    /**
     * 任务流水号
     */
    @XmlElement(name = "DATAITEM")
    @JsonProperty(value = "DATAITEM")
    private DataUploadResponseBodyDataItem dataItem;

    public DataUploadResponseBodyDataItem getDataItem() {
        return dataItem;
    }

    public void setDataItem(DataUploadResponseBodyDataItem dataItem) {
        this.dataItem = dataItem;
    }
}
