package zcq.myjpa.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/09
 */
public class FileUtils {

    /**
     *获取服务器文件 字节数组
     * @param fileUrl 文件地址
     * @return
     */
    public static byte[] getFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            httpUrl.setConnectTimeout(5 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            httpUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            httpUrl.connect();
            int httpResult = httpUrl.getResponseCode();
            //判断是否连接成功
            if (httpResult == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpUrl.getInputStream();
                //写入byte数组
                byte[] buffer = readInputStream(inputStream);
                inputStream.close();
                return buffer;
            } else {
                throw new RuntimeException("获取文件服务器图片失败");
            }
        } catch (IOException e) {
            throw new RuntimeException("获取文件服务器图片失败");
        }
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inputStream)
            throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 获取服务器文件 base64流
     * @param fileUrl
     * @return
     */
    public static String fileToBase64(String fileUrl) {
        return new BASE64Encoder().encode(getFile(fileUrl));
    }

    /**
     * base64流转pdf图片
     * @param base64Content
     * @return
     * @throws Exception
     */
    public static BufferedImage pdfToImage(String base64Content) throws Exception {
        byte [] b = new BASE64Decoder().decodeBuffer(base64Content);
        InputStream inputStream = new ByteArrayInputStream(b);
        PDDocument doc = PDDocument.load(inputStream);
        PDFRenderer renderer = new PDFRenderer(doc);
        BufferedImage imageToSave = renderer.renderImage(0, 2.0f);
        inputStream.close();
        return imageToSave;
    }



    /**
     * 生成二维码
     * @param content
     * @return
     * @throws Exception
     */
    public static BufferedImage createImage(String content) throws Exception {
        //二维码尺寸
        final int QRCODE_SIZE = 300;
        //编码
        final String CHARSET = "utf-8";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     *
     * @param image
     * @param resp
     */
    public static void imageOutput(BufferedImage image, HttpServletResponse resp) {
        try {
            ImageIO.write(image, "PNG", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
