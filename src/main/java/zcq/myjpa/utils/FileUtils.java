package zcq.myjpa.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/09
 */
public class FileUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 获取服务器文件 字节数组
     *
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
     * base64
     *
     * @param content
     * @return
     */
    public static String base64Encode(byte[] content) {
        return new BASE64Encoder().encode(content);
    }

    public static byte[] base64Decode(String base64Content) throws IOException {
        return new BASE64Decoder().decodeBuffer(base64Content);
    }

    public static String imageToBase64(BufferedImage img) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpeg",baos);
        final byte[] bytes = baos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 =  encoder.encodeBuffer(bytes).trim();
        return base64;
    }

    public static BufferedImage base64ToImage(String img) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = decoder.decodeBuffer(img);
        InputStream inputStream = new ByteArrayInputStream(b);
        return ImageIO.read(inputStream);
    }

    /**
     * 转图片
     *
     * @param b
     * @return
     * @throws Exception
     */
    public static BufferedImage pdfToImage(byte[] b) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(b);
        PDDocument doc = PDDocument.load(inputStream);
        PDFRenderer renderer = new PDFRenderer(doc);int numberOfPages = doc.getNumberOfPages();
        logger.info("读取pdf（页数为{})，开始转为BufferedImage",numberOfPages);
        long mill = System.currentTimeMillis();
        BufferedImage bufferedImage = null;
        if (numberOfPages == 1) {
            bufferedImage = renderer.renderImage(0, 2.0f);
        } else if (numberOfPages > 1) {
            List<BufferedImage> imageToSaves = new ArrayList<>();
            for (int i = 0; i < numberOfPages; i++) {
                BufferedImage imageToSave = renderer.renderImage(i, 2.0f);
                imageToSaves.add(imageToSave);
            }
            logger.info("开始拼接BufferedImage");
            bufferedImage = concactBufferedImages(imageToSaves);
            logger.info("拼接BufferedImage完成");
        }
        inputStream.close();
        doc.close();
        logger.info("用时 {} ms",System.currentTimeMillis()-mill);
        return bufferedImage;
    }

    /**
     * 拼接BufferedImage
     * @param images
     * @return
     */
    public static BufferedImage concactBufferedImages(List<BufferedImage> images) {
        int heightTotal = 0;
        for(int j = 0; j < images.size(); j++) {
            heightTotal += images.get(j).getHeight();
        }
        int heightCurr = 0;
        BufferedImage concatImage = new BufferedImage(images.get(0).getWidth(), heightTotal, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = concatImage.createGraphics();
        for(int j = 0; j < images.size(); j++) {
            g2d.drawImage(images.get(j), 0, heightCurr, null);
            heightCurr += images.get(j).getHeight();
        }
        g2d.dispose();
        return concatImage;
    }

    public static String handleDpiRange(String base64Src, int minSize, int maxSize, int width, int height) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(base64Src);
            InputStream inputStream = new ByteArrayInputStream(b);
            int available = inputStream.available();
            if (available <= 0) {
                return null;
            }
            if (minSize < available && available < maxSize) {
                return base64Src;
            }
            BufferedImage img = ImageIO.read(inputStream);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            float softenFactor = 0.01f;
            float[] softenArray = {0, softenFactor, 0, softenFactor, 1 - softenFactor * 4, softenFactor, 0, softenFactor, 0};
            Kernel kernel = new Kernel(3, 3, softenArray);
            ConvolveOp convolveOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
            convolveOp.filter(tag, null);
            tag.getGraphics().drawImage(img, 0, 0, width, height, null);
            return imageToBase64(tag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码
     *
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
     * 输出图片
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

    /**
     * 读取为字节
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static byte[] fileToBytes(String fileUrl) throws Exception {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.connect();
            int httpResult = conn.getResponseCode();
            if (httpResult == 200) {
                InputStream inputStream = conn.getInputStream();
                final ByteArrayOutputStream output = new ByteArrayOutputStream();
                IOUtils.copy(inputStream,output);
                inputStream.close();
                output.flush();
                output.close();
                return output.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }


}
