package com.example.nettyDev.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/10 11:39
 */
public class ClassicThread {
    private static ExecutorService fixedService = Executors.newFixedThreadPool(2);
    private static ExecutorService singleService = Executors.newSingleThreadExecutor();
    private static ExecutorService cachedService = Executors.newCachedThreadPool();
    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
    public static void main(String[] args) {
        /**
         * ctl 线程池状态控制参数
         * */
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY   = (1 << COUNT_BITS) - 1;
        //00011111111111111111111111111111

        System.out.println(Integer.toBinaryString(-1));
        //11111111111111111111111111111111
        int RUNNING    = -1 << COUNT_BITS;
        //11100000000000000000000000000000

        //不再接收新的任务，但是会继续处理workqueue和正在处理的任务
        int SHUTDOWN   =  0 << COUNT_BITS;
        //0000000000000000000000000000000

        //不接收新任务，也不处理阻塞队列，中断正在执行的任务
        int STOP       =  1 << COUNT_BITS;
        //0100000000000000000000000000000

        //过渡阶段，线程池即将terminated
        int TIDYING    =  2 << COUNT_BITS;

        //线程池已经停止
        //1000000000000000000000000000000
        int TERMINATED =  3 << COUNT_BITS;
        //1100000000000000000000000000000

        System.out.println(CAPACITY);

        for (int j = 0; j < 10; j++) {
            fixedService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(Thread.currentThread().getName()+"："+i);
                    }
                }
            });
        }

    }
}
