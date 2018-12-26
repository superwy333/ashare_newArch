package exception;


/**
 * @Author: TanZhang
 * @Description:
 * @Date: Created in 10:17 2018/1/29
 * @Modified By:
 */
public class BusinessException extends RuntimeException {

    private String errorCode;

    public BusinessException(){super();}

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message,String errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
