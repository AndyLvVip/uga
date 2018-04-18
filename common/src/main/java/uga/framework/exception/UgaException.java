package uga.framework.exception;

/**
 * @Author: andy.lv
 * @Date: created on 2018/2/5
 * @Description:
 */
public class UgaException extends RuntimeException {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public enum Error {
        INTERNAL_SERVER_ERROR("500", "Internal server error"),
        PARAM_ERROR("PARAM_500", "parameter error"),
        ;

        public final String code;
        public final String msg;

        Error(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public UgaException(Throwable t) {
        super(t);
        this.code = Error.INTERNAL_SERVER_ERROR.code;
    }

    private UgaException(String code, String message) {
        super(message);
        this.code = code;
    }

    public UgaException(Error e, String message) {
        this(e.code, message);
    }
}
