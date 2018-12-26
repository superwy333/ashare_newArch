package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.domain.BizAreaField;

public class BizAreaFieldModel extends BizAreaField {

    private String formFieldName;

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

}