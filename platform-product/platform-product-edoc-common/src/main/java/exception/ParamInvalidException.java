package exception;

import com.zynsun.openplatform.util.ExecuteResult;

/**
 * 自定义异常校验类
 */
public class ParamInvalidException extends RuntimeException {

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 返回错误信息
     */
    private ExecuteResult<String> errorResult;


    private static final long serialVersionUID = 9211208169923396115L;

    public ParamInvalidException() {
        super();
    }

    public ParamInvalidException(String message) {
        super(message);
    }

    public ParamInvalidException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ParamInvalidException(String message, ExecuteResult<String> errorResult) {
        super(message);
        this.errorResult = errorResult;
    }

    public ParamInvalidException(String message, String errorCode, ExecuteResult errorResult) {
        super(message);
        this.errorCode = errorCode;
        this.errorResult = errorResult;
    }

    public ExecuteResult<String> getErrorResult() {
        return errorResult;
    }

    public void setErrorResult(ExecuteResult<String> errorResult) {
        this.errorResult = errorResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
