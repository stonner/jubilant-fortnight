package zcq.myjpa.exceptions;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/21
 */

public class PermissionException extends RuntimeException{
    public PermissionException() {}

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(Throwable cause) {
        super(cause);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
