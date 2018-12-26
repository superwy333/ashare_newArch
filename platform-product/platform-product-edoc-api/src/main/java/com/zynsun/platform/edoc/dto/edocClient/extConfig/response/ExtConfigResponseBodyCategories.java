package com.zynsun.platform.edoc.dto.edocClient.extConfig.response;

import com.zynsun.platform.edoc.dto.edocClient.baseConfig.response.BaseConfigResponseBodyCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 16:38
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class ExtConfigResponseBodyCategories implements Serializable {

    /**
     * 扩展业务定义-公司
     */
    @XmlElement(name = "COMPANY")
    @JsonProperty(value = "COMPANY")
    private List<ExtConfigResponseBodyCompany> company;

    public List<ExtConfigResponseBodyCompany> getCompany() {
        return company;
    }

    public void setCompany(List<ExtConfigResponseBodyCompany> company) {
        this.company = company;
    }
}
