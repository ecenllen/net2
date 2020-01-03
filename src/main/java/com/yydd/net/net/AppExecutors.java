package com.yydd.net.net;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {

    // 操作数据库线程池
    private static final Executor diskIO = Executors.newSingleThreadExecutor();

    // 操作网络线程池
    private static final ExecutorService networkIO = Executors.newFixedThreadPool(3);


    public static void runNetworkIO(Runnable runnable) {
        networkIO.execute(runnable);
    }

    public static void runDbIO(Runnable runnable) {
        diskIO.execute(runnable);
    }
}
