package com.zynsun.platform.edoc.dto.edocClient.baseConfig.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 15:09
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class BaseConfigResponseBodyDocTypeRelation implements Serializable {

    /**
     * 单证类型信息
     */
    @XmlElement(name = "DOC_TYPE")
    @JsonProperty(value = "DOC_TYPE")
    private List<BaseConfigResponseBodyDocType> docType;

    public List<BaseConfigResponseBodyDocType> getDocType() {
        return docType;
    }

    public void setDocType(List<BaseConfigResponseBodyDocType> docType) {
        this.docType = docType;
    }
}
