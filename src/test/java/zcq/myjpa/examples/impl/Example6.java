package zcq.myjpa.examples.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import zcq.myjpa.entity.Bill;
import zcq.myjpa.examples.Example;
import zcq.myjpa.vo.ReceiptStageVo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void doing3(){
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "wechat");
        map.put("name", "lucy");
        Bill bill = new Bill();
        try {
            BeanUtils.populate(bill,map);
            System.out.println(JSON.toJSONString(bill));
            System.out.println(JSON.toJSONString(map));

            Map<String, String> describe = BeanUtils.describe(bill);
            System.out.println(JSON.toJSONString(describe));
            System.out.println(JSON.toJSONString(bill));

            Bill bill1 = new Bill();
            BeanUtils.copyProperties(bill1,bill);
            System.out.println(JSON.toJSONString(bill1));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
