package zcq.myjpa;

import zcq.myjpa.examples.Example;
import zcq.myjpa.examples.impl.Example1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/13
 */
public class TestMain {
    public static void main(String[] args) {
        compareRunningTime(new Example1(),(int)Math.pow(10,7));
    }

    public static void compareRunningTime(Example example, int cycleNum) {
        long time1 = getRunningTime(example, 1, cycleNum);
        long time2 = getRunningTime(example, 2, cycleNum);
        System.out.println("time1: "+time1+" ms");
        System.out.println("time2: "+time2+" ms");
    }

    public static long getRunningTime(Example example, int methodNum, int cycleNum) {
        Class<? extends Example> clazz = example.getClass();
        Method method = null;
        try {
            method = clazz.getMethod("doing" + methodNum);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        long start = System.currentTimeMillis();
        for (int i=0;i<cycleNum;i++) {
            try {
                method.invoke(example);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        return end-start;
    }
}
