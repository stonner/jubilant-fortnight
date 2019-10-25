package zcq.myjpa.examples;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import org.springframework.util.LinkedMultiValueMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/16
 */
public class Example3 {

    private static final Map<String,String> CUSTOMERCODE_COMPANY_MAP = new HashMap<String,String>(){{
        put("11", "原特区内");
        put("12", "原特区内");
        put("13", "莲塘供水服务有限公司");
        put("14", "深水宝安水务集团有限公司");
        put("15", "深水龙岗水务集团有限公司");
        put("16", "深水光明水务有限公司");
        put("17", "深水龙华水务有限公司");
        put("31", "深圳市龙岗坪地供水有限公司");
        put("32", "深圳市南澳供水有限公司");
    }};

    public static void main(String[] args) {
        new Example3().doing4();
    }

    public void doing1() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
        try {
            final Date parse = format.parse("2018-01-11T09:18:10");
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void doing2() {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(null,"a");
        map.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

    }

    public void doing3() {
        CUSTOMERCODE_COMPANY_MAP.put("t", "234");
        CUSTOMERCODE_COMPANY_MAP.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });

    }

    public void doing4() {
        final String noExist = CUSTOMERCODE_COMPANY_MAP.get("noExist");
        System.out.println(noExist);
    }
}
