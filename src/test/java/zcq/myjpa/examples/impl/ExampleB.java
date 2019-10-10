package zcq.myjpa.examples.impl;

import com.google.common.collect.Maps;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.util.CollectionUtils;
import zcq.myjpa.examples.Example;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/10/10
 */
public class ExampleB implements Example {
    public static void main(String[] args) {
        new ExampleB().doing1();
    }

    @Override
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

    @Override
    public void doing2() throws IOException, InvalidFormatException {

    }
}
