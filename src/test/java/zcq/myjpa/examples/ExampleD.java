package zcq.myjpa.examples;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import javafx.scene.control.RadioMenuItem;
import org.omg.CORBA.StringHolder;

import javax.xml.transform.sax.SAXTransformerFactory;
import java.util.Random;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/11/29
 */
public class ExampleD {
    private static final Random random = new Random();

    public static void main(String[] args) {
        final long l = System.currentTimeMillis();

        for (int i = 0; i < 10000; i++) {
            System.out.println(doing3());
        }

        System.out.println(System.currentTimeMillis() - l);
    }


    public static String doing1() {
        final StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < 9) {
            stringBuilder.append(random.nextInt(9));
        }
        return stringBuilder.toString();
    }

    public static String doing2() {
        return random.nextInt(1000000000) +  "";
    }

    public static String doing3() {
        final String s = String.valueOf((Math.random() + 1) * 1000000000);
        return s.substring(2);
    }
}
