package zcq.myjpa.bean;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
public class ResponseObject {
    private static final long serialVersionUID = -7771374323162062273L;
    public static final int STATUS_CODE_SUCCESS = 0;
    public static final int STATUS_CODE_FAILURE = 300;
    public static final int STATUS_CODE_TIMEOUT = 301;
    public static final int STATUS_INVALID_TIMESTAMP = 303;
    public static final int STATUS_CODE_FORBIDDEN = 403;
    public static final int STATUS_CODE_NOTFOUND = 404;
    public static final int STATUS_CODE_SERVEERROR = 500;
    public static final int STATUS_LOGIN_UNKNOWNACCOUNT = 1000;
    public static final int STATUS_LOGIN_INCORRECTCREDENTIALS = 1001;
    public static final int STATUS_LOGIN_CLIENTIDNOTFOUND = 1002;
    public static final int STATUS_LOGIN_CAPTCHA = 1003;
    public static final int STATUS_LOGIN_AUTHENTICATION = 1004;
    public static final int STATUS_LOGIN_DISABLEDACCOUNT = 1005;
    public static final int STATUS_FORCE_UPDATE = 1006;
    public static final int STATUS_DEVICE_NOT_REGISTER = 1007;
    public static final int STATUS_DEVICE_NOT_AUDIT = 1008;
    public static final int STATUS_ACCOUNT_NOT_ACTIVE = 1009;
    public static final int STATUS_NO_DOMAIN_IN_PUBCLOUD = 1010;
    private int state = 0;
    private String message = "";
    private Object data = "";

    public static ResponseObject newOk(String message) {
        return new ResponseObject(0, message);
    }

    public static ResponseObject newOk(String message, Object data) {
        return new ResponseObject(0, message, data);
    }

    public static ResponseObject newError(String message) {
        return new ResponseObject(300, message);
    }

    public static ResponseObject newError(String message, Object data) {
        return new ResponseObject(300, message, data);
    }

    public static ResponseObject newTimeout(String message) {
        return new ResponseObject(301, message);
    }

    public static ResponseObject newTimeout(String message, Object data) {
        return new ResponseObject(301, message, data);
    }

    public static ResponseObject newForbidden(String message) {
        return new ResponseObject(301, message);
    }

    public static ResponseObject newForbidden(String message, Object data) {
        return new ResponseObject(301, message, data);
    }

    public static ResponseObject newResponseObject(int state, String message, Object data) {
        return new ResponseObject(state, message, data);
    }

    public static ResponseObject newResponseObject(int state, String message) {
        return new ResponseObject(state, message);
    }

    public ResponseObject() {
    }

    public ResponseObject(int state) {
        this.state = state;
    }

    public ResponseObject(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResponseObject(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = null == data ? "" : data;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
