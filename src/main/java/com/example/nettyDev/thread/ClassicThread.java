package com.example.nettyDev.thread;

import java.util.PriorityQueue;
import java.util.concurrent.*;

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
        //必须配置参数，基于数组的有界阻塞队列，共使用一个ReentrantLock
        new ArrayBlockingQueue<>(5);
        //可以不配置参数，基于链表的无界阻塞队列，队头队尾各使用一个ReentrantLock，dque双端队列，吞吐量高于ArrayBlockingQueue
        new LinkedBlockingDeque<>();
        //无解实时队里，队列里不持任何任务，接收到任务后立马创建线程进行执行，
        new SynchronousQueue<>();
        //以下两个都是优先级队列，好像是基于堆排序算法进行实现（堆排序，是一个满二叉树，对于大顶堆每个节点的值都大于其左右子节点对应的值。小顶堆与之相反）
        new DelayQueue<>();
        new PriorityQueue<>();

    }
}
