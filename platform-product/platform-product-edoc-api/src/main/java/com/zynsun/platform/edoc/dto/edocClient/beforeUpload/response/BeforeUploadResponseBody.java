package com.zynsun.platform.edoc.dto.edocClient.beforeUpload.response;

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
public class BeforeUploadResponseBody implements Serializable {

    /**
     * 任务流水号
     */
    @XmlElement(name = "BILL_HEADER_ID")
    @JsonProperty(value = "BILL_HEADER_ID")
    private String edocHeaderId;

    /**
     * 当前页数
     */
    @XmlElement(name = "BILL_PAGE")
    @JsonProperty(value = "BILL_PAGE")
    private String billPage;

    public String getEdocHeaderId() {
        return edocHeaderId;
    }

    public void setEdocHeaderId(String edocHeaderId) {
        this.edocHeaderId = edocHeaderId;
    }

    public String getBillPage() {
        return billPage;
    }

    public void setBillPage(String billPage) {
        this.billPage = billPage;
    }
}
