package zcq.myjpa.utils;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/10/16
 */
public class CipherUtils {

    private final static String DES = "DES";

    private final static String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    private static final String HEXSTRING = "0123456789ABCDEF";

    public static String bytes2HexStr(byte[] bytes) {
        StringBuilder resultStrignBuilder = new StringBuilder();
        StringBuilder hexStrignBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            hexStrignBuilder.append(HEXSTRING.charAt((aByte & 0xF0) >> 4));
            hexStrignBuilder.append(HEXSTRING.charAt(aByte & 0x0F));
            resultStrignBuilder.append(hexStrignBuilder);
        }
        return resultStrignBuilder.toString();
    }

    public static byte[] hexStr2Bytes(String src) {
        int l = src.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; i++) {
            result[i] = uniteBytes(src.charAt(i * 2), src.charAt(i * 2 + 1));
        }
        return result;
    }

    private static byte uniteBytes(char src0, char src1) {
        byte b0 = Byte.decode("0x" + src0);
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode("0x" + src1);
        return (byte) (b0 | b1);
    }


    private static byte[] encrypt(byte[] src, byte[] key) {
        return crypt(src, key, Cipher.ENCRYPT_MODE);
    }

    private static byte[] decrypt(byte[] src, byte[] key) {
        return crypt(src, key, Cipher.DECRYPT_MODE);
    }

    private static byte[] crypt(byte[] src, byte[] key, int mode) {
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec dks = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(mode, securekey, sr);
            return cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
