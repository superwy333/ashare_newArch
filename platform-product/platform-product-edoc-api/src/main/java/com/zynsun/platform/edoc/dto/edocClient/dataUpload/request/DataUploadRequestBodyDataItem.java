package com.zynsun.platform.edoc.dto.edocClient.dataUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadRequestBodyDataItem implements Serializable {

    /**
     * 单证条目
     */
    @XmlElement(name = "ITEM")
    @JsonProperty(value = "ITEM")
    private List<DataUploadRequestBodyItem> items;

    public List<DataUploadRequestBodyItem> getItems() {
        return items;
    }

    public void setItems(List<DataUploadRequestBodyItem> items) {
        this.items = items;
    }
}
