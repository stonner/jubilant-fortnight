package zcq.myjpa.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/09
 */
public class HttpClientUtils {
    /*** httpClient **/
    private CloseableHttpClient httpClient;

    /*** httpPost **/
    private HttpPost httpPost;

    /*** httpGet **/
    private HttpGet httpGet;

    /*** httpGet **/
    private HttpResponse response;

    /*** requestConfig **/
    private RequestConfig requestConfig;

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 构造函数
     */
    public HttpClientUtils() {
        // 忽略https
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (Exception e) {
            LOGGER.debug("设置SSL连接失败，将使用普通连接", e);
            httpClient = HttpClients.createDefault();
        }
        requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();// 设置请求和传输超时时间
    }

    /**
     * 因为每个实例内置的httpClient的连接池是共享且不能复用的，所以最好每次获取新实例使用
     *
     * @return 获取新实例
     */
    public static HttpClientUtils newInstance() {
        return new HttpClientUtils();
    }

    /**
     * @param url 请求url
     * @param url 参数
     * @return boolean
     */
    public String get(String url) {
        String body = null;
        try {
            httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);// 执行请求
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                body = parseResponse(response);
            }
        } catch (Exception e) {
            LOGGER.error("Get请求出现异常", e);
        } finally {
            closeHttpClient(null, httpGet);
        }
        return body;
    }

    /**
     * @param url 请求url
     * @param params 参数
     * @param retryNum 重试请求
     * @return boolean
     */
    public String post(String url, Map<String, String> params, int retryNum) {
        // 构造请求发送实体
        List<NameValuePair> nvps = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);
        // 构造请求头
        Map<String, String> headers = new HashMap<>();
        // ---begin解决中文乱码问题
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Accept-Language", "zh-cn");
        headers.put("Accept-Encoding", "gzip, deflate");
        // ---end
        // 执行请求
        return this.doPost(url, entity, headers, retryNum);
    }

    /**
     * 上传
     *
     * @param url 请求url
     * @param params 参数
     * @param is 文件流
     * @param filename 文件名
     * @return
     */
    public String postFile(String url, Map<String, String> params, InputStream is, String filename) {
        // 构造文件发送实体
        MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(StandardCharsets.UTF_8);
        for (Map.Entry<String, String> param : params.entrySet()) {
            builder.addTextBody(param.getKey(), param.getValue());
        }
        builder.addBinaryBody("file", is, ContentType.APPLICATION_OCTET_STREAM, filename);
        HttpEntity entity = builder.build();
        // 构造请求头
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept-Language", "zh-cn");
        // 执行请求
        return this.doPost(url, entity, headers, 1);
    }

    /**
     * 执行POST请求
     *
     * @param url 请求地址
     * @param entity 请求内容
     * @param headers 请求头
     * @param retryLeft 尝试发送次数
     * @return 响应结果的字符串形式
     */
    private String doPost(String url, HttpEntity entity, Map<String, String> headers, int retryLeft) {
        String body = null;
        if (retryLeft > 0) {
            try {
                httpPost = new HttpPost(url);
                httpPost.setConfig(requestConfig);
                httpPost.setEntity(entity);
                if (headers != null) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        httpPost.addHeader(header.getKey(), header.getValue());
                    }
                }
                response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    body = parseResponse(response);
                }
            } catch (SocketTimeoutException ste) {
                LOGGER.error("获取响应数据超时", ste);
                this.doPost(url, entity, headers, retryLeft - 1);
            } catch (Exception e) {
                LOGGER.error("post请求出现异常", e);
            } finally {
                closeHttpClient(httpPost, null);
            }
        }
        return body;
    }

    /**
     * @param resp response
     * @return String
     */
    private String parseResponse(HttpResponse resp) {
        String body = null;
        try {
            body = EntityUtils.toString(resp.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            LOGGER.error("解析返回结果对象出现异常", e);
        }
        return body;
    }

    /**
     * 关闭连接客户端
     *
     * @param post post
     * @param get get
     */
    private void closeHttpClient(HttpPost post, HttpGet get) {
        try {
            if (post != null) {
                post.abort();
            }
            if (get != null) {
                httpGet.abort();
            }
            httpClient.close();
        } catch (Exception e) {
            LOGGER.error("关闭连接出现异常", e);
        }
    }
}
