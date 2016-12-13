package org.example.seed.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Ricardo Pina Arellano on 24/11/2016.
 */
@Configuration
@EnableAsync
@EnableCaching
public class ServiceConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setMaxPoolSize(55);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(25);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        taskExecutor.initialize();

        final ConcurrentTaskExecutor concurrentTaskExecutor = new ConcurrentTaskExecutor();

        concurrentTaskExecutor.setConcurrentExecutor(taskExecutor);

        return concurrentTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
