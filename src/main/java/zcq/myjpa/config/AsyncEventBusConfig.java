package zcq.myjpa.config;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Configuration
public class AsyncEventBusConfig {
    @Bean
    @Scope("singleton")
    public AsyncEventBus asyncEventBus() {
        final ThreadPoolTaskExecutor executor = executor();
        AsyncEventBus asyncEventBus = new AsyncEventBus(executor);
        return asyncEventBus;
    }

    @Bean
    public ThreadPoolTaskExecutor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(1000);
        // executor.setKeepAliveSeconds(600);
        // executor.setAllowCoreThreadTimeOut(true);
        return executor;
    }
}
