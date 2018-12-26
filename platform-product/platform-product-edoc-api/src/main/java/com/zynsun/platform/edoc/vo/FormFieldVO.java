package com.zynsun.platform.edoc.vo;

import java.io.Serializable;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-22 09:19
 **/
public class FormFieldVO implements Serializable {

    private Long id;

    private String formFieldName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }
}
