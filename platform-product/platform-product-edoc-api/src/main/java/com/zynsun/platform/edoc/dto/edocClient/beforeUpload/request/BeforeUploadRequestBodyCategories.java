package com.zynsun.platform.edoc.dto.edocClient.beforeUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 16:38
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BeforeUploadRequestBodyCategories implements Serializable {

    /**
     * 扩展业务定义-公司
     */
    @XmlElement(name = "COMPANY")
    @JsonProperty(value = "COMPANY")
    private BeforeUploadRequestBodyCompany company;

    public BeforeUploadRequestBodyCompany getCompany() {
        return company;
    }

    public void setCompany(BeforeUploadRequestBodyCompany company) {
        this.company = company;
    }
}
