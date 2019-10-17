package zcq.myjpa.utils;

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

    private enum CalculationType{
        AND, OR, XOR, NON;
    }

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

    private static byte[] calculateBytes(byte[] src1, byte[] src2, CalculationType type) {
        if (src1 == null || src2 == null) {
            return null;
        }
        final int length = src1.length;
        if (length == 0 || length != src2.length) {
            return null;
        }
        final byte[] bytes = new byte[length];
        switch (type) {
            case OR:
                for (int i = 0; i < length; i++) {
                    bytes[i] = (byte) (src1[i] | src2[i]);
                }
                break;
            case AND:
                for (int i = 0; i < length; i++) {
                    bytes[i] = (byte) (src1[i] & src2[i]);
                }
                break;
            case XOR:
                for (int i = 0; i < length; i++) {
                    bytes[i] = (byte) (src1[i] ^ src2[i]);
                }
                break;
            default:
                break;
        }
        return bytes;
    }

    public static void main(String[] args) {
        final byte[] bytes = "111111".getBytes();
        final byte[] bytes1 = "381214".getBytes();
        System.out.println((byte)0xFF);
        System.out.print(" ");
        System.out.print("    ");
        System.out.print("  ");
        System.out.print("    ");
        System.out.print("0 ");
        System.out.print("    ");
        System.out.print("1 ");
        System.out.print("    ");
        System.out.print("~ ");
        System.out.print("    ");
        System.out.print("& ");
        System.out.print("    ");
        System.out.print("^ ");
        System.out.print("    ");
        System.out.print("| ");
        System.out.println("    \n");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(((bytes[i] & 0xFF) ^ (bytes1[i] & 0xFF)));
            System.out.print("    ");
            System.out.print(bytes[i] ^ bytes1[i]);
            System.out.print("    ");
            System.out.print(bytes[i]);
            System.out.print("    ");
            System.out.print((bytes1[i]));
            System.out.print("    ");
            System.out.print(~ bytes1[i]);
            System.out.print("    ");
            System.out.print((bytes1[i] & 0xFF));
            System.out.print("    ");
            System.out.print((bytes1[i] ^ 0xFF));
            System.out.print("    ");
            System.out.print((bytes1[i] | 0xFF));
            System.out.println("    ");
        }

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
