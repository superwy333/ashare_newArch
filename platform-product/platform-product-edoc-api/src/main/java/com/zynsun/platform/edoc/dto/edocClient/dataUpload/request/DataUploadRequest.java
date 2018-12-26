package com.zynsun.platform.edoc.dto.edocClient.dataUpload.request;

import com.zynsun.platform.edoc.dto.edocClient.RequestHeader;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

 /**
 * 客户端上传数据请求
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 13:50
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "REQUEST")
@XmlType(name = "", propOrder = {"header", "body"})
public class DataUploadRequest implements Serializable {

    /**
     * 请求头信息
     */
    @XmlElement(name = "HEADER")
    @JsonProperty(value = "HEADER")
    private RequestHeader header;

    /**
     * 请求参数信息
     */
    @XmlElement(name = "PARAMETER")
    @JsonProperty(value = "PARAMETER")
    private DataUploadRequestBody body;

    public RequestHeader getHeader() {
        return header;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public DataUploadRequestBody getBody() {
        return body;
    }

    public void setBody(DataUploadRequestBody body) {
        this.body = body;
    }
}
