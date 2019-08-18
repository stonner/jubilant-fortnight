package zcq.myjpa.examples.impl;
/**
 * ***************************************************************************
 * Copyright (C) 2017 ShenZhen ComTop Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 * ****************************************************************************
 */

import zcq.myjpa.examples.Example;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/16
 */
public class Example3 implements Example {
    public static void main(String[] args) {
        new Example3().doing1();
    }

    @Override
    public void doing1() {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss");
        try {
            final Date parse = format.parse("2018-01-11T09:18:10");
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doing2() {

    }
}
