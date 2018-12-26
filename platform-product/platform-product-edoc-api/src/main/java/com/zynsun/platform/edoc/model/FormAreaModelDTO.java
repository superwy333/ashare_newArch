package com.zynsun.platform.edoc.model;

import com.zynsun.platform.edoc.dto.dynamicForm.FormAreaDTO;
import com.zynsun.platform.edoc.dto.dynamicForm.FormFieldDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-21 18:51
 **/
public class FormAreaModelDTO extends FormAreaDTO {
    private String formFieldIds;
    private String formFieldNames;
    private List<FormFieldDTO> formFieldList = new ArrayList<>();

    public String getFormFieldIds() {
        return formFieldIds;
    }

    public void setFormFieldIds(String formFieldIds) {
        this.formFieldIds = formFieldIds;
    }

    public String getFormFieldNames() {
        return formFieldNames;
    }

    public void setFormFieldNames(String formFieldNames) {
        this.formFieldNames = formFieldNames;
    }

    public List<FormFieldDTO> getFormFieldList() {
        return formFieldList;
    }

    public void setFormFieldList(List<FormFieldDTO> formFieldList) {
        this.formFieldList = formFieldList;
    }
}
