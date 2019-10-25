package zcq.myjpa.examples;

import org.junit.Test;
import zcq.myjpa.utils.LoUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class ExampleC {

    public static void main(String[] args) {
        try {
            String s = "1114156505 +1114470004";
            byte[] gbks = s.getBytes("GBK");
            byte[] utf8s = s.getBytes(StandardCharsets.UTF_8);
            System.out.println(s);
            System.out.println(LoUtils.bin2HexStr(gbks));
            System.out.println(LoUtils.bin2HexStr(utf8s));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main1() {
        String password = "235548";
        String bankCardNo = "6217582000004602405";
        String mainKey = "7368D50BC88549B56285DC5BEC253BB3";
        String encryptedPinkey = "B87D5EDCDCEAB3D89ED9541075B56059590DA47B";
        String encryptPin = LoUtils.encryptPin(password, bankCardNo, mainKey, encryptedPinkey);
        System.out.println(encryptPin);
        String decryptPin = LoUtils.decryptPin(encryptPin, bankCardNo, mainKey, encryptedPinkey);
        System.out.println(decryptPin);
    }
}
