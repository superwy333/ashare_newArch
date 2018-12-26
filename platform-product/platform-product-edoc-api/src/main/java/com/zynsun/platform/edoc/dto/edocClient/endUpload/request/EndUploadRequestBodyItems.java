package com.zynsun.platform.edoc.dto.edocClient.endUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * 客户端结束上次请求任务信息集合
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class EndUploadRequestBodyItems implements Serializable {

    /**
     * 任务信息
     */
    @XmlElement(name = "NODE")
    @JsonProperty(value = "NODE")
    private List<EndUploadRequestBodyItem> items;

    public List<EndUploadRequestBodyItem> getItems() {
        return items;
    }

    public void setItems(List<EndUploadRequestBodyItem> items) {
        this.items = items;
    }
}
