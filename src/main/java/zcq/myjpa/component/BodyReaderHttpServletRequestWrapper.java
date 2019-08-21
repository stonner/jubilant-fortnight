package zcq.myjpa.component;
/******************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 ******************************************************************************/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcq.myjpa.utils.HttpUtils;

import java.io.*;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 重写HttpServletRequestWrapper的getInputStream()
 * 使request支持多次getInputStream()
 * @author xuzhifan
 * @version 1.0
 * @since 2017/7/21
 */
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private static Logger log = LoggerFactory.getLogger(BodyReaderHttpServletRequestWrapper.class);

    //request inputStream中数据的线程独立本地变量副本
    private ThreadLocal<byte[]>  bytesLocal = new ThreadLocal<byte[]>();

    /** 读取request inputSteam
     * @param request request
     */
    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        byte[] bytes = HttpUtils.readBody(request);
        //存入本地副本
        bytesLocal.set(bytes);
    }

    /**
     * 重写getReader()
     */
    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 重写getInputStream()
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //数据从本地副本获取
        final ByteArrayInputStream bais = new ByteArrayInputStream(bytesLocal.get());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }
            @Override
            public boolean isReady() {
                return false;
            }
            @Override
            public void setReadListener(ReadListener readListener) {
            }
            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}

