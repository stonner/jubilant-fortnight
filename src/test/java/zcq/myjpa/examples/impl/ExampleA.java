package zcq.myjpa.examples.impl;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import zcq.myjpa.examples.Example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/24
 */
public class ExampleA implements Example {
    public static void main(String[] args) {
        new ExampleA().doing1();

    }

    @Override
    public void doing1(){
        String[] strings = {"happy", "birthday", "hello"};
//        System.out.println(Arrays.stream(strings).filter((s) -> s.contains("y")).collect(Collectors.joining(",")));
        try (Stream stream = Arrays.stream(strings).filter((s) -> s.contains("y"))) {
            ((List) (stream.collect(Collectors.toList()))).forEach(System.out::println);
        }
    }

    @Override
    public void doing2(){

    }
}
