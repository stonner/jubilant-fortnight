package zcq.myjpa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springfox.documentation.service.MediaTypes;
import zcq.myjpa.common.Constants;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/21
 */
public class HttpUtils {

    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /** 读取request body中数据
     * @param request request
     * @return
     */
    public static byte[] readBody (HttpServletRequest request) {

        byte[] bytes = new byte[0];
        String contentType = request.getContentType();
        //form表单提交方式不处理
        if (Constants.MediaTypes.APPLICATION_FORM.equals(contentType)) {
            return bytes;
        }
        int contentLength = request.getContentLength();
        if (contentLength > 0) {
            InputStream is = null;
            DataInputStream di = null;
            try {
                is = request.getInputStream();
                bytes = new byte[contentLength];
                di = new DataInputStream(is);
                di.readFully(bytes);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                close(is, di);
            }
        }
        return bytes;

    }

    /**
     * 关闭流
     */
    private static void close(InputStream is, DataInputStream di) {

        try {
            if (di != null) {
                di.close();
            }
            if (is != null) {
                is.close();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
