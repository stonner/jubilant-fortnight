package zcq.myjpa;

import zcq.myjpa.examples.Example;
import zcq.myjpa.examples.impl.Example1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/13
 */
public class TestMain {
    public static void main(String[] args) {
        final List<String> strings = runAllMethods(new Example1(), (int) Math.pow(10, 6));
        for (String string : strings) {
            System.out.println(string);
        }
        //compareRunningTime(new Example1(),new int[]{1,2},(int)Math.pow(10,5));
    }

    public static void compareRunningTime(Example example,int[] methodNos, int cycleNum) {
        long[] times = new long[methodNos.length];
        for (int i = 0; i < times.length; i++) {
            times[i] = getRunningTime(example, i+1, cycleNum);
        }
        for (int i = 0; i < times.length; i++) {
            System.out.println("time"+(i+1)+": "+times[i]+" ms");
        }
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

    public static List<String> runAllMethods(Example example, int cycleNum) {
        final ArrayList<String> list = new ArrayList<>();
        Class<? extends Example> clazz = example.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            long start = System.currentTimeMillis();
            for (int j=0;j<cycleNum;j++) {
                try {
                    methods[i].invoke(example);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            long end = System.currentTimeMillis();
            list.add("method \""+name+"\" used time: "+(end-start)+" ms");
        }
        return list;
    }
}
