package zcq.myjpa.utils;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/05
 */
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.net.ssl.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;

/**
 * 银联报文工具类
 * @author huanglong
 * @since 2017/7/27
 * @version 1.0
 */
public class LoUtils {

    protected static Logger log = LoggerFactory.getLogger(LoUtils.class);

    /**
     * 发包路径
     */
    private static String bankMessUrl = "/home/yulicheng/ibh/";

    /**
     * des向量
     */
    private final static String DES = "DES";

    /**
     * des向量
     */
    private final static String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    /**
     * 是否有数据
     */
    public static final String IS_DATA = "1";

    /**
     * 转16进制
     */
    private static final String hexStr = "0123456789ABCDEF";

    /**
     * 解析主密钥-key
     */
//    private static final String MAIN_KEY = "97531BDF86420ACEFEDCBA0123456789";

    /**
     * 转2进制
     */
    private static final String[] binaryArray =
            {"0000", "0001", "0010", "0011",
                    "0100", "0101", "0110", "0111",
                    "1000", "1001", "1010", "1011",
                    "1100", "1101", "1110", "1111"};

    /**
     * 十六进制字符串转换成bytes
     *
     * @param src
     * @return
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m),
                    src.substring(m, n));
        }
        return ret;
    }

    /**
     * 获取字节
     *
     * @param src0
     * @param src1
     * @return
     */
    private static byte uniteBytes(String src0, String src1) {
        byte b0 = Byte.decode("0x" + src0).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     * 将二进制数组转换为十六进制字符串  2-16
     *
     * @param bytes
     * @return
     */
    public static String bin2HexStr(byte[] bytes) {

        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;  //+" "
        }
        return result;
    }

    /**
     * 2进制转16进制
     *
     * @param binary
     * @return
     */
    public static String b2h(String binary) {
        // 这里还可以做些判断，比如传进来的数字是否都是0和1
        int length = binary.length();
        int temp = length % 4;
        // 每四位2进制数字对应一位16进制数字
        // 补足4位
        if (temp != 0) {
            for (int i = 0; i < 4 - temp; i++) {
                binary = "0" + binary;
            }
        }
        // 重新计算长度
        length = binary.length();
        StringBuilder sb = new StringBuilder();
        // 每4个二进制数为一组进行计算
        for (int i = 0; i < length / 4; i++) {
            int num = 0;
            // 将4个二进制数转成整数
            for (int j = i * 4; j < i * 4 + 4; j++) {
                num <<= 1;// 左移
                num |= (binary.charAt(j) - '0');// 或运算
            }
            // 直接找到该整数对应的16进制，这里不用switch来做
            sb.append(hexStr.charAt(num));
        }
        return sb.toString();
    }

    /**
     * 二进制数组转换为二进制字符串   2-2
     *
     * @param bArray
     * @return
     */
    public static String bytes2BinStr(byte[] bArray) {
        String outStr = "";
        int pos = 0;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            //低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos];
        }
        return outStr;
    }

    /**
     * 获取字符长度
     *
     * @param length 长度
     * @param index  数位
     * @param toHex  是否转16进制
     * @return
     */
    public static String doLenToString(int length, int index, boolean toHex) {
        String len = length + "";
        //判断是否需要转16进制
        if (toHex) {
            len = Integer.toHexString(length);
        }
        //如果小于数位用0补位
        while (len.length() < index) {
            len = "0" + len;
        }
        return len;
    }

    /**
     * 处理https GET/POST请求
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方法
     * @param outputStr     参数
     **/
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {
        //创建SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        TrustManager[] tm = {new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        //初始化
        sslContext.init(null, tm, new java.security.SecureRandom());
        //获取SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });

        //创建路径
        URL url = new URL(requestUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Charsert", "utf-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data");
        //设置当前实例使用的SSLSoctetFactory
        conn.setSSLSocketFactory(ssf);
        conn.connect();
        //创建文件
        String u = bankMessUrl;
        String fileName = UUID.randomUUID().toString();
        File file = new File(u + fileName + ".txt");
        //判断有则删除文件
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        //报文保存到文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(hexStr2Bytes(outputStr));
        fileOutputStream.flush();
        fileOutputStream.close();
        //往服务器端写内容
        if (null != outputStr) {
            OutputStream os = conn.getOutputStream();
            os.write(getBytes(file));
            os.close();
        }

        //读取服务器端返回的内容
        InputStream is = conn.getInputStream();
        int available = 0;
        //如果没有拿到，每1秒拿一次，一共五次，拿不到报超时
        for(int i = 0 ; i < 5 ; i++){
            available = is.available();
            if(available > 0){
                break;
            }
            Thread.sleep(1000);
        }
        byte[] b = new byte[available];
        is.read(b);
        is.close();
        //判断有则删除文件
        if (file.exists()) {
            file.delete();
        }
        return bin2HexStr(b).toUpperCase();
    }

    /**
     * 获得指定文件的byte数组
     *
     * @param file
     */
    private static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * mac计算
     *
     * @param key   mac秘钥
     * @param Input 待加密数据
     * @return
     */
    public static String getMac(byte[] key, byte[] Input) {
        int length = Input.length;
        int x = length % 8;
        // 需要补位的长度
        int addLen = 0;
        if (x != 0) {
            addLen = 8 - length % 8;
        }
        int pos = 0;
        // 原始数据补位后的数据
        byte[] data = new byte[length + addLen];
        System.arraycopy(Input, 0, data, 0, length);
        byte[] oper1 = new byte[8];
        System.arraycopy(data, pos, oper1, 0, 8);
        pos += 8;
        // 8字节异或
        for (int i = 1; i < data.length / 8; i++) {
            byte[] oper2 = new byte[8];
            System.arraycopy(data, pos, oper2, 0, 8);
            byte[] t = bytesXOR(oper1, oper2);
            oper1 = t;
            pos += 8;
        }
        // 将异或运算后的最后8个字节（RESULT BLOCK）转换成16个HEXDECIMAL：
        byte[] resultBlock = bytesToHexString(oper1).getBytes();
        // 取前8个字节MAK加密
        byte[] front8 = new byte[8];
        System.arraycopy(resultBlock, 0, front8, 0, 8);
        byte[] behind8 = new byte[8];
        System.arraycopy(resultBlock, 8, behind8, 0, 8);
        byte[] desfront8 = encrypt(front8, key);
        // 将加密后的结果与后8 个字节异或：
        byte[] resultXOR = bytesXOR(desfront8, behind8);
        // 用异或的结果TEMP BLOCK 再进行一次单倍长密钥算法运算
        byte[] buff = encrypt(resultXOR, key);
        // 将运算后的结果（ENC BLOCK2）转换成16 个HEXDECIMAL asc
        byte[] retBuf = new byte[8];
        // 取8个长度字节就是mac值
        System.arraycopy(bytesToHexString(buff).getBytes(), 0, retBuf, 0, 8);
        return bin2HexStr(retBuf);
    }

    /**
     * 单字节异或
     *
     * @param src1
     * @param src2
     * @return
     */
    private static byte byteXOR(byte src1, byte src2) {
        return (byte) ((src1 & 0xFF) ^ (src2 & 0xFF));
    }

    /**
     * 字节数组异或
     *
     * @param src1
     * @param src2
     * @return
     */
    private static byte[] bytesXOR(byte[] src1, byte[] src2) {
        int length = src1.length;
        if (length != src2.length) {
            return null;
        }
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = byteXOR(src1[i], src2[i]);
        }
        return result;
    }

    /**
     * 字节数组转HEXDECIMAL
     *
     * @param bArray
     * @return
     */
    private static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }


    /**
     * 加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回加密后的数据
     */
    private static byte[] encrypt(byte[] src, byte[] key) {
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     */
    public static byte[] decrypt(byte[] src, byte[] key) {
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3des加密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     */
    public static byte[] desEncrypt(String src, String key) {
        byte[] lk = new byte[8];
        byte[] rk = new byte[8];
        byte[] bkey = hexStr2Bytes(key);
        byte[] bsrc = hexStr2Bytes(src);
        System.arraycopy(bkey, 0, lk, 0, 8);
        System.arraycopy(bkey, 8, rk, 0, 8);
        //3des 双倍加密双法
        byte[] srcBytes = encrypt(decrypt(encrypt(bsrc, lk), rk), lk);
        return srcBytes;
    }

    /**
     * 3des解密
     *
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return 返回解密后的原始数据
     */
    public static byte[] desDecrypt(String src, String key) {
        byte[] lk = new byte[8];
        byte[] rk = new byte[8];
        byte[] bkey = hexStr2Bytes(key);
        byte[] bsrc = hexStr2Bytes(src);
        System.arraycopy(bkey, 0, lk, 0, 8);
        System.arraycopy(bkey, 8, rk, 0, 8);
        //3des 双倍解密双法
        byte[] srcBytes = decrypt(encrypt(decrypt(bsrc, lk), rk), lk);
        return srcBytes;
    }

    /**
     * 解密mak工作密钥
     *
     * @param main 主密钥-密文
     * @param mak  工作密钥
     * @return
     */
    public static String makDecrypt(String main, String mak) {
        if (StringUtils.isBlank(main) || main.length() != 32) {
            log.error("主密钥获取失败");
            return "";
        }
        if (StringUtils.isBlank(mak) || mak.length() != 40) {
            log.error("工作密钥mak获取失败");
            return "";
        }
        String makKey = mak.substring(0, 16);    //mak密钥密文
        String makData = mak.substring(16, 32);  //需要被解析的密钥16个0
        String checkvalue = mak.substring(32, 40);//检查值
        //解密主密钥
//        String mainKey = bin2HexStr(desDecrypt(main, MAIN_KEY));
        String mainKey = main;
        //解密工作密钥
        byte[] byteMakKey = desDecrypt(makKey, mainKey);
        //单倍加密8字节0
        String data = bin2HexStr(encrypt(hexStr2Bytes(makData), byteMakKey));
        //判断是否解密正确
        if (data.startsWith(checkvalue)) {
            return bin2HexStr(byteMakKey);
        }
        log.error("工作密钥mak验证失败");
        return "";
    }

    /**
     * 解密tdk工作密钥
     *
     * @param main 主密钥-密文
     * @param tdk  工作密钥
     * @return
     */
    public static String tdkDecrypt(String main, String tdk) {
        if (StringUtils.isBlank(main) || main.length() != 32) {
            log.error("主密钥获取失败");
            return "";
        }
        if (StringUtils.isBlank(tdk) || tdk.length() != 40) {
            log.error("工作密钥pink获取失败");
            return "";
        }
        //解密主密钥
//        String mainKey = bin2HexStr(desDecrypt(main, MAIN_KEY));
        String mainKey = main;

        String tdkKey = tdk.substring(0, 32);    //pink密钥密文
        String checkvalue = tdk.substring(32, 40);//检查值

        //解密工作密钥
        tdk = bin2HexStr(desDecrypt(tdkKey, mainKey));

        //对8个0做双倍加密
        String data = bin2HexStr(desEncrypt("0000000000000000",tdk));

        //判断是否解密正确
        if (data.startsWith(checkvalue)) {
            return tdk;
        }
        log.error("工作密钥tdk验证失败");
        return "";
    }

    /**
     * 加密磁道信息
     * @param track
     * @param key
     * @return
     */
    public static String encryptTrack(String track, String key){
        //奇数补F
        if(track.length() % 2 != 0){
            track = track + "F";
        }
        //截取左2开始8个字节字符
        String data = track.substring(track.length()-18,track.length()-2);
        //加密磁道截出部分
        String encryptdata = bin2HexStr(desEncrypt(data,key));
        //磁道截出部分加密后替换原先的明文数据部分
        track = track.replace(data,encryptdata);
        //截取37位去掉后面的F
        if(track.length() > 37){
            return track.substring(0,37);
        }
        return track;
    }

    /**
     * 加密pin
     * @param password
     * @param bankCardNo
     * @param mainKey
     * @param encryptedPinKey
     * @return
     */
    public static String encryptPin(String password, String bankCardNo, String mainKey, String encryptedPinKey) {
        String pin = "06"+password+"FFFFFFFF";
        final String pan = getPan(bankCardNo);
        //异或
        final byte[] bytes = hexStr2Bytes(pin);
        final byte[] bytes1 = hexStr2Bytes(pan);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= bytes1[i];
        }
        final String str = bin2HexStr(bytes);
        //pinKey明文
        final String pinKey = tdkDecrypt(mainKey, encryptedPinKey);
        return bin2HexStr(desEncrypt(str, pinKey));
    }

    /**
     * 解密pin
     * @param encryptedPassword
     * @param bankCardNo
     * @param mainKey
     * @param encryptedPinKey
     * @return
     */
    public static String decryptPin(String encryptedPassword, String bankCardNo, String mainKey, String encryptedPinKey) {
        final String pinKey = tdkDecrypt(mainKey, encryptedPinKey);
        byte[] bytes = desDecrypt(encryptedPassword, pinKey);
        final String pan = getPan(bankCardNo);
        //异或
        final byte[] bytes1 = hexStr2Bytes(pan);
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= bytes1[i];
        }
        final String str = bin2HexStr(bytes);
        return str.substring(2,8);
    }

    /**
     * 卡号从右数第二位开始取12位，前补0至16位
     * @param bankCardNo
     * @return
     */
    public static String getPan(String bankCardNo) {
        return "0000" + bankCardNo.substring(bankCardNo.length() - 13, bankCardNo.length() - 1);
    }

    /**
     * md5 32位算法
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String md5 = new BigInteger(1, md.digest()).toString(16);
            //BigInteger会把0省略掉，需补全至32位
            return fillMD5(md5);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密错误:"+e.getMessage(),e);
        }
    }

    /**
     * md5补位32位
     * @param md5
     * @return
     */
    public static String fillMD5(String md5){
        return md5.length()==32?md5:fillMD5("0"+md5);
    }

}

