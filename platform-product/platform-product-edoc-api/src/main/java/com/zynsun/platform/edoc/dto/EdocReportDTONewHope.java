package com.zynsun.platform.edoc.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-08-09 21:29
 **/
public class EdocReportDTONewHope {

    @JsonProperty("F_NAME")
    private String fName;

    @JsonProperty("F_REC_UN")
    private String fRecUn;

    @JsonProperty("F_REC_ED")
    private String fRecEd;

    @JsonProperty("F_CHECK_UN")
    private String fCheckUn;

    @JsonProperty("F_CHECK_ED")
    private String fCheckEd;

    @JsonProperty("F_ARC_UN")
    private String fArcUn;

    @JsonProperty("F_ARC_ED")
    private String fArcEd;

    @JsonProperty("F_ARC_REV")
    private String fArcRev;

    @JsonProperty("F_REC_REV")
    private String fRecrev;


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfRecUn() {
        return fRecUn;
    }

    public void setfRecUn(String fRecUn) {
        this.fRecUn = fRecUn;
    }

    public String getfRecEd() {
        return fRecEd;
    }

    public void setfRecEd(String fRecEd) {
        this.fRecEd = fRecEd;
    }

    public String getfCheckUn() {
        return fCheckUn;
    }

    public void setfCheckUn(String fCheckUn) {
        this.fCheckUn = fCheckUn;
    }

    public String getfCheckEd() {
        return fCheckEd;
    }

    public void setfCheckEd(String fCheckEd) {
        this.fCheckEd = fCheckEd;
    }

    public String getfArcUn() {
        return fArcUn;
    }

    public void setfArcUn(String fArcUn) {
        this.fArcUn = fArcUn;
    }

    public String getfArcEd() {
        return fArcEd;
    }

    public void setfArcEd(String fArcEd) {
        this.fArcEd = fArcEd;
    }

    public String getfArcRev() {
        return fArcRev;
    }

    public void setfArcRev(String fArcRev) {
        this.fArcRev = fArcRev;
    }

    public String getfRecrev() {
        return fRecrev;
    }

    public void setfRecrev(String fRecrev) {
        this.fRecrev = fRecrev;
    }
}
