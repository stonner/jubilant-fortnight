package zcq.myjpa.examples.impl;

import zcq.myjpa.entity.Product;
import zcq.myjpa.examples.Example;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/27
 */
public class Example5 implements Example {

    private String pname = "789";

    private ThreadLocal<Product> productThreadLocal = ThreadLocal.withInitial(() -> new Product(pname));

    @Override
    public void doing1() {
        final Product product = productThreadLocal.get();
        if (!"f".equals(product.getName())) {
            System.out.println("------------------1");
        }
        product.setName("f");
    }

    @Override
    public void doing2() {
        final Product product = productThreadLocal.get();
        if (!"s".equals(product.getName())) {
            System.out.println("------------------2");
        }
        product.setName("s");
    }
}
