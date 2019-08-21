package zcq.myjpa.component.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import zcq.myjpa.bean.ResponseObject;
import zcq.myjpa.exceptions.PermissionException;
import zcq.myjpa.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @param request HttpServletRequest
     * @param e Exception
     * @return ResponseObject
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseObject handler (HttpServletRequest request, Exception e) {
        if (e instanceof ServiceException) {
            return ResponseObject.newError("服务异常"+e.getMessage());
        } else if (e instanceof PermissionException) {
            return ResponseObject.newError("权限异常"+e.getMessage());
        } else{
            logger.error("error url: "+request.getRequestURL());
            logger.error(e.getMessage(),e);
            return ResponseObject.newError("系统异常");
        }
    }

    /**
     * @param request HttpServletRequest
     * @param e DataAccessException
     * @return ResponseObject
     */
    @ResponseBody
    @ExceptionHandler(DataAccessException.class)
    public ResponseObject sqlHandler (HttpServletRequest request ,DataAccessException e) {
        logger.error("error url: "+request.getRequestURL());
        logger.error("数据访问异常："+e.getMessage(),e);
        return ResponseObject.newError("dao异常");
    }

}
