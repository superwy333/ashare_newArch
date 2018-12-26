package com.zynsun.openplatform.util;

import java.io.Serializable;

public class KpClientExecuteResult<T> implements Serializable {


    private static final long serialVersionUID = -742696149451958164L;
    /**
     * 结果代码
     */
    private String code;
    
    /**
     *  业务数据
     */
    private T data;
    
    /**
     * 执行结果标识
     */
    private Integer flag;
    
    /**
     * 查询结果信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
