package com.zynsun.platform.edoc.dto.edocClient.dataUpload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/04 10:49
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class DataUploadResponseBodyDataItem implements Serializable {

    /**
     * 单证条目
     */
    @XmlElement(name = "ITEM")
    @JsonProperty(value = "ITEM")
    private List<DataUploadResponseBodyItem> items;

    public List<DataUploadResponseBodyItem> getItems() {
        return items;
    }

    public void setItems(List<DataUploadResponseBodyItem> items) {
        this.items = items;
    }
}
