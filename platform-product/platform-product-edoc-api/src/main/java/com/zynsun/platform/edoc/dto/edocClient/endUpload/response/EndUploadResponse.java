package com.zynsun.platform.edoc.dto.edocClient.endUpload.response;

import com.zynsun.platform.edoc.dto.edocClient.AbstractResponse;
import com.zynsun.platform.edoc.dto.edocClient.ResponseHeader;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * 结束上传接口响应
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/03 11:14
 */
@XmlRootElement(name = "RESPONSE")
@XmlType(name = "", propOrder = {"header"})
public class EndUploadResponse extends AbstractResponse implements Serializable {

    /**
     * 响应头信息
     */
    @XmlElement(name = "RESPONSE_HEADER")
    @JsonProperty(value = "RESPONSE_HEADER")
    private ResponseHeader header;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    @Override
    public void setResponseHeader(ResponseHeader header) {
        setHeader(header);
    }
}
