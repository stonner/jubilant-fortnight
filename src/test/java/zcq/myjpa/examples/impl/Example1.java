package zcq.myjpa.examples.impl;

import zcq.myjpa.examples.Example;

import java.util.Random;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/13
 */
public class Example1 implements Example {
    @Override
    public void doing1() {
        Random rd = new Random();
        String code = "";
        for (int i=0;i<4;i++){
            code += rd.nextInt(10);
        }
        System.out.println(code);
    }

    @Override
    public void doing2() {
        Random rd = new Random();
        String code = rd.nextInt(10000)+"";
        while (code.length()<4) {
            code = "0"+code;
        }
        System.out.println(code);
    }
}
