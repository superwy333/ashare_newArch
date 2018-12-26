package com.zynsun.platform.edoc.dto.edocClient;

import java.io.Serializable;

/**
 * 应答抽象类
 *
 * @Author：zhitong.cao
 * @Date：Created in 2018/01/05 16:19
 */
public abstract class AbstractResponse {
    public abstract void setResponseHeader(ResponseHeader header);
}
