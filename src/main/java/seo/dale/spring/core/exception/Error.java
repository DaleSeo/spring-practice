package seo.dale.spring.core.exception;

public class Error {

    private static final ErrorCode DEFAULT_CODE = ErrorCode.UNDEFIND;
    private static final String DEFAULT_MESSAGE = "Un expected exception.";

    private ErrorCode code;
    private String message;

    public Error() {
        this(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    public Error(ErrorCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
