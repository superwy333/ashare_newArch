package com.zynsun.openplatform.util;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.zynsun.openplatform.constants.CodeConstant;

public class ErrorMsgUtil {

    public static String msgConverter(List<ObjectError> errors) {
        StringBuffer msg = new StringBuffer();
        if (errors != null) {
            for (ObjectError error : errors) {
                msg.append(error.getDefaultMessage() + " ");
            }
        }
        return msg.toString();
    }

    public static ExecuteResult<Object> invalidResult(BindingResult bindingResult) {
        return ErrorMsgUtil.error("参数错误.", ErrorMsgUtil.msgConverter(bindingResult.getAllErrors()), CodeConstant.PARAMTER_ERROR_CODE);
    }

    public static ExecuteResult<Object> error(String title, String msg, int errorCode) {
        ExecuteResult<Object> result = new ExecuteResult<Object>();
        result.setCode(String.valueOf(errorCode));
        result.setTitle(title);
        result.addErrorMessage(msg);
        return result;
    }
}
