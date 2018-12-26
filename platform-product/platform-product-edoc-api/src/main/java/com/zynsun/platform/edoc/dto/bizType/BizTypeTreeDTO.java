package com.zynsun.platform.edoc.dto.bizType;

import java.io.Serializable;

/**
 * @Author：FeiyueYang
 * @Description：
 * @Date：Created in 2017/6/3 0003 下午 4:26
 * @Modified By：
 */
public class BizTypeTreeDTO implements Serializable {

    private Long id;
    private Long bizTypePid;
    private String bizTypePName;
    private String bizTypeCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBizTypePid() {
        return bizTypePid;
    }

    public void setBizTypePid(Long bizTypePid) {
        this.bizTypePid = bizTypePid;
    }

    public String getBizTypePName() {
        return bizTypePName;
    }

    public void setBizTypePName(String bizTypePName) {
        this.bizTypePName = bizTypePName;
    }

    public String getBizTypeCode() {
        return bizTypeCode;
    }

    public void setBizTypeCode(String bizTypeCode) {
        this.bizTypeCode = bizTypeCode;
    }
}
