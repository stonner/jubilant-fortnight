package zcq.myjpa.examples.impl;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import zcq.myjpa.examples.Example;
import zcq.myjpa.vo.ReceiptStageVo;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/02
 */
public class Example6 implements Example {
    private static final String hexStr = "0123456789ABCDEF";

    @Override
    @Test
    public void doing1() {
        final String s = "{\"teacct\":\"0944000047449996\"}";
        final byte[] bytes;
        bytes = s.getBytes(StandardCharsets.US_ASCII);
        try {
            System.out.println(bytes.length);
            for (byte aByte : bytes) {
                System.out.print(aByte);
                System.out.print(" - ");
            }
            final String gbk;
            gbk = new String(bytes, "GBK");
            System.out.println(gbk);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final StringBuffer buffer = new StringBuffer();
        for (byte aByte : bytes) {
            buffer.append(hexStr.charAt((aByte & 0XF0) >> 4))
                    .append(hexStr.charAt((aByte & 0X0F)));
        }
        System.out.println(buffer);

        for (int i = 0; i < buffer.length(); i+=2) {
            byte b = Byte.decode(buffer.substring(i, i + 2)).byteValue();
        }
    }

    @Override
    @Test
    public void doing2() {

        List<ReceiptStageVo> receiptStageVos = JSON.parseArray("[\n" +
                "    {\n" +
                "        \"wateritemname\": \"垃圾费\",\n" +
                "        \"ladderunit\": 6000,\n" +
                "        \"billmonth\": 201907,\n" +
                "        \"waterusagename\": \"居民用水\",\n" +
                "        \"ladderprice\": 0.59,\n" +
                "        \"billingladderid\": 985977344,\n" +
                "        \"userid\": 8089409,\n" +
                "        \"laddersn\": 1,\n" +
                "        \"landercost\": 3540\n" +
                "    },\n" +
                "    {\n" +
                "        \"wateritemname\": \"污水费\",\n" +
                "        \"ladderunit\": 6000,\n" +
                "        \"billmonth\": 201907,\n" +
                "        \"waterusagename\": \"居民用水\",\n" +
                "        \"ladderprice\": 0.9,\n" +
                "        \"billingladderid\": 985977345,\n" +
                "        \"userid\": 8089409,\n" +
                "        \"laddersn\": 1,\n" +
                "        \"landercost\": 5400\n" +
                "    },\n" +
                "    {\n" +
                "        \"wateritemname\": \"水费\",\n" +
                "        \"ladderunit\": 700000000,\n" +
                "        \"billmonth\": 201907,\n" +
                "        \"waterusagename\": \"居民用水\",\n" +
                "        \"ladderprice\": 2.67,\n" +
                "        \"billingladderid\": 985977346,\n" +
                "        \"userid\": 8089409,\n" +
                "        \"laddersn\": 1,\n" +
                "        \"landercost\": 186900.731\n" +
                "    }\n" +
                "]", ReceiptStageVo.class);
        for (ReceiptStageVo receiptStageVo : receiptStageVos) {
            System.out.println(JSON.toJSONString(receiptStageVo));
        }
        //  700000000 太大
    }
}
