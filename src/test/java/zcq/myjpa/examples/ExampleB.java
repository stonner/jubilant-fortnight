package zcq.myjpa.examples;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/10/10
 */
public class ExampleB {
    public static void main(String[] args) {
        new ExampleB().doing2();
    }

    public void doing1() {
        final HashMap<Integer, Integer> map = Maps.newHashMap();

        int[] arr = {2, 4, 15, 23, 46, 666};
        for (int i : arr) {
            Integer value = map.merge(i, i, (o, d) -> o * d);
            System.out.println(value);
        }
        for (int i : arr) {
            Integer value = map.merge(i, 4, (o, d) -> o * d);
            System.out.println(value);
        }
        map.compute(3, (key, oldValue) -> {
            System.out.println(key);
            System.out.println(oldValue);
            if (null == oldValue) {
                return (int)Math.pow(key, 2);
            } else {
                return oldValue.intValue() + key;
            }
        });

        map.forEach((k,v)-> System.out.println(k+ ":" + v));

    }

    public void doing2() {
        byte b1 = (byte) 17;
        System.out.println(b1);
        byte b2 = (byte) 32;
        final int i = b1 ^ b2;
        System.out.println(i);
        byte b3 = (byte) i;
        final int i1 = b3 ^ b2;
        System.out.println(i1);
        final byte i11 = (byte) i1;
        System.out.println(i11);

    }
}
