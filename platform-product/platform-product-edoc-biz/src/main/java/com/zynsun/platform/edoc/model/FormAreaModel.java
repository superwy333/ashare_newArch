package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.domain.FormArea;
import com.zynsun.platform.edoc.domain.FormField;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 22:34
 **/
public class FormAreaModel extends FormArea{

    public List<FormField> formFieldList = new ArrayList<>();

    public List<FormField> getFormFieldList() {
        return formFieldList;
    }

    public void setFormFieldList(List<FormField> formFieldList) {
        this.formFieldList = formFieldList;
    }
}
