package com.zynsun.platform.edoc.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * 接口返回信息实体
 * @author Liangjiawei
 * @date 2018/5/28 10:39
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RESPONSE")
public class EdocHeaderRes implements Serializable{
    /**
     * 返回体头部
     */
    @XmlElement(name="RESPONSE_HEADER")
    private ResponseHeader responseHeader=new ResponseHeader();
    /**
     * 返回体内容
     */
    @XmlElement(name="RESPONSE_CONTEXT")
    private ResponseContext responseContext=new ResponseContext();

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ResponseHeader implements Serializable {
        /**
         * 头部状态
         */
        @XmlElement(name = "RESPONSE_STATUS")
        private String responseStatus;
        /**
         * 头部信息
         */
        @XmlElement(name = "RESPONSE_MESSAGE")
        private String responseMessage;


        public String getResponseStatus() {
            return responseStatus;
        }

        public void setResponseStatus(String responseStatus) {
            this.responseStatus = responseStatus;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    public static  class ResponseContext implements Serializable {
        /**
         * 请求返回信息内容
         */
        @XmlElement(name = "MSG")
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public ResponseContext getResponseContext() {
        return responseContext;
    }

    public void setResponseContext(ResponseContext responseContext) {
        this.responseContext = responseContext;
    }
}
