package com.zynsun.platform.edoc.dto.edocClient.endUpload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 客户端结束上次请求参数
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:52
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class EndUploadRequestBody implements Serializable {

    /**
     * 用户代码
     */
    @XmlElement(name = "USER_CODE")
    @JsonProperty(value = "USER_CODE")
    private String userCode;

    /**
     * 任务信息集合
     */
    @XmlElement(name = "NODES")
    @JsonProperty(value = "NODES")
    private EndUploadRequestBodyItems nodes;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public EndUploadRequestBodyItems getNodes() {
        return nodes;
    }

    public void setNodes(EndUploadRequestBodyItems nodes) {
        this.nodes = nodes;
    }
}
