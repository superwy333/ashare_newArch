package com.zynsun.openplatform.exception;


public class ParamInvalidException extends IllegalArgumentException {



    private static final long serialVersionUID = 9211208169923396115L;

    public ParamInvalidException() {
    }

    public ParamInvalidException(String message) {
        super(message);
    }

}
