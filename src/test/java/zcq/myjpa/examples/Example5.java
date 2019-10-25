package zcq.myjpa.examples;

import org.junit.Test;
import zcq.myjpa.entity.Product;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/27
 */
public class Example5 {

    private String pname = "789";

    private ThreadLocal<Product> productThreadLocal = ThreadLocal.withInitial(() -> new Product(pname));

    public void doing1() {
        final Product product = productThreadLocal.get();
        if (!"f".equals(product.getName())) {
            System.out.println("------------------1");
        }
        product.setName("f");
    }

    public void doing2() {
        final Product product = productThreadLocal.get();
        if (!"s".equals(product.getName())) {
            System.out.println("------------------2");
        }
        product.setName("s");
    }

    @Test
    public void doing3() {
        final List<Integer> integers = Arrays.asList(1, 2,7);
        System.out.println(integers.get(1));
        final boolean b = integers instanceof List;
        System.out.println(b);
    }

    @Test
    public void doing4() {
        final int i = '6' - '0';
        System.out.println(i);
    }

    @Test
    public void doing5() {
        int i = 7;
        i <<= 1;
        System.out.println(i);
        i |= 64;
        System.out.println(i);

    }
}
