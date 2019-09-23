package zcq.myjpa.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/09/23
 */
public class ThreadPoolConfig {

    /**
     * 核心线程数、不会关闭
     */
    private final static int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数、当核心线程数的任务队列满时，会创建新的线程、新线程加核心线程总数不会超过最大线程数
     */
    private final static int MAX_POOL_SIZE = 10;
    /**
     * 非核心线程存活时间
     */
    private final static long KEEP_ALIVE_TIME = 1;
    /**
     * 线程任务队列
     */
    private final static int CAPACITY = 500;

    private final static ThreadFactory NAME_THREAD_FACTORY = new ThreadFactoryBuilder()
            .setNameFormat("thread-pool-%d").build();

    public final static ThreadPoolExecutor EXECUTOR_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(CAPACITY),NAME_THREAD_FACTORY, new ThreadPoolExecutor.AbortPolicy());

    private ThreadPoolConfig() {}

}
