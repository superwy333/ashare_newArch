package com.zynsun.openplatform.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicyea on 2016/4/27.
 */
public class ExecuteResult<T> implements Serializable {

    private static final long serialVersionUID = 692208363790729494L;

    /** 执行结果代码 */
    private String code;
    /** 执行结果标题 */
    private String title;
    /** 执行结果数据 */
    private T result;
    /** 执行成功消息 */
    private String successMessage;
    /** 执行错误信息 */
    private List<String> errorMessages = new ArrayList<String>();
    /** 字段错误信息 */
    private Map<String, String> fieldErrors = new HashMap<String, String>();
    /** 执行警告信息 */
    private List<String> warningMessages = new ArrayList<String>();

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public boolean isSuccess() {
        return (this.errorMessages.isEmpty()) && (this.fieldErrors.isEmpty());
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Map<String, String> getFieldErrors() {
        return this.fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public List<String> getWarningMessages() {
        return this.warningMessages;
    }

    public void setWarningMessages(List<String> warningMessages) {
        this.warningMessages = warningMessages;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorMessages.add(errorMessage);
    }

    public void addFieldError(String field, String errorMessage) {
        this.fieldErrors.put(field, errorMessage);
    }

    public void addWarningMessage(String warningMessage) {
        this.warningMessages.add(warningMessage);
    }

    @Override
    public String toString() {
        return "ExecuteResult{" +
                "success='" + isSuccess() + '\'' +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", successMessage='" + successMessage + '\'' +
                ", errorMessages=" + errorMessages +
                ", fieldErrors=" + fieldErrors +
                ", warningMessages=" + warningMessages +
                '}';
    }
}
