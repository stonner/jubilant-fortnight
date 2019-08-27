package zcq.myjpa.examples.impl;

import zcq.myjpa.entity.Bill;
import zcq.myjpa.examples.Example;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/27
 */
public class Example4 implements Example {
    private Bill billLocal = new Bill();

    private ThreadLocal<Bill> billThreadLocal = new ThreadLocal<Bill>(){
        @Override
        protected Bill initialValue() {
            return new Bill();
        }
    };

    @Override
    public void doing1() {
        final Bill bill = billLocal;
        //final Bill bill = billThreadLocal.get();
        if (!"f".equals(bill.getName())) {
            System.out.println("------------------1");
        }
        bill.setName("f");
    }

    @Override
    public void doing2() {
        final Bill bill = billLocal;
        //final Bill bill = billThreadLocal.get();
        if (!"s".equals(bill.getName())) {
            System.out.println("------------------2");
        }
        bill.setName("s");
    }

    public void doing3() {
        final Bill bill = billLocal;
        //final Bill bill = billThreadLocal.get();
        if (!"d".equals(bill.getName())) {
            System.out.println("------------------3");
        }
        bill.setName("d");
    }
}
