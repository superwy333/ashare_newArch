package webservice.fssc.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 历史冲销凭证集
 */
public class F_CXPZ_LIST implements Serializable {
    private static final long serialVersionUID = 3242620755823917461L;

    /**
     * 凭证编号
     */
    @JSONField(name = "F_PZBH")
    private String f_PZBH;

    /**
     * 冲销凭证编号
     */
    @JSONField(name = "F_CXPZBH")
    private String f_CXPZBH;

    public String getF_PZBH() {
        return f_PZBH;
    }

    public void setF_PZBH(String f_PZBH) {
        this.f_PZBH = f_PZBH;
    }

    public String getF_CXPZBH() {
        return f_CXPZBH;
    }

    public void setF_CXPZBH(String f_CXPZBH) {
        this.f_CXPZBH = f_CXPZBH;
    }
}
