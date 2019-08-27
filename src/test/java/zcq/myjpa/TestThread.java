package zcq.myjpa;

import zcq.myjpa.examples.Example;
import zcq.myjpa.examples.impl.Example1;
import zcq.myjpa.examples.impl.Example4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/27
 */
public class TestThread {

    public static void main(String[] args) {
        runAll(new Example4(), (int) Math.pow(10, 3));
    }

    private static void runAll(Example example, int cycleNum) {
        Class<? extends Example> clazz = example.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            final String name = method.getName();
            new Thread(()->{
                for (int j=0;j<cycleNum;j++) {
                    try {
                        method.invoke(example);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println( "method "+ name);
        }
    }
}
