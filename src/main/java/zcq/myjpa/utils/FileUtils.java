package zcq.myjpa.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/09
 */
public class FileUtils {

    /**
     *获取文件服务器图片
     * @param docServerUrl 文件服务器地址
     * @param fid 文件名
     * @return
     */
    public byte[] getFile(String docServerUrl, String fid) {
        try {
            URL url = new URL(docServerUrl + fid + ".sv");
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            int httpResult = httpUrl.getResponseCode();
            //判断是否连接成功
            if (httpResult == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpUrl.getInputStream();
                //写入byte数组
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[inputStream.available()];
                int rc = 0;
                while ((rc = inputStream.read(buffer, 0, 100)) > 0) {
                    swapStream.write(buffer, 0, rc);
                }
                buffer = swapStream.toByteArray();
                inputStream.close();
                buffer.clone();
                return buffer;
            } else {
                throw new RuntimeException("获取文件服务器图片失败");
            }
        } catch (IOException e) {
            throw new RuntimeException("获取文件服务器图片失败");
        }
    }
}
