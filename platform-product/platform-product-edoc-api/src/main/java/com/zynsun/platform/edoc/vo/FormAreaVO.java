package com.zynsun.platform.edoc.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: xihao.ding
 * @create: 2018-05-22 09:19
 **/
public class FormAreaVO implements Serializable {

    private List<FormFieldVO> formFieldVOList ;

    private Long id;

    private String formAreaName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormAreaName() {
        return formAreaName;
    }

    public void setFormAreaName(String formAreaName) {
        this.formAreaName = formAreaName;
    }

    public List<FormFieldVO> getFormFieldVOList() {
        return formFieldVOList;
    }

    public void setFormFieldVOList(List<FormFieldVO> formFieldVOList) {
        this.formFieldVOList = formFieldVOList;
    }
}
