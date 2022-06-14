package com.example.nettyDev.thread.c003_cas;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author SunHang
 * @description:  性能对比 AtomicLong、LongAdder（采用分段锁，并发很高的时候会有优势）、synchronized
 *                 在不同场景下，性能表现不同，与线程数、并发数有关系
 * @date 2022/5/28 10:21
 */
public class T3_AtomicVsSyncVsLongAdder {
    static long count1 = 0L;
    static AtomicLong count2 = new AtomicLong(0);
    static LongAdder count3 = new LongAdder();


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] =  new Thread(()->{
                for (int i1 = 0; i1 < 10000; i1++) {
                    count2.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("atomic"+ (end - start));


        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] =  new Thread(()->{
                for (int i1 = 0; i1 < 10000; i1++) {
                    synchronized (lock) {
                        count1++;
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("synchronized"+ (end - start));



        for (int i = 0; i < threads.length; i++) {
            threads[i] =  new Thread(()->{
                for (int i1 = 0; i1 < 10000; i1++) {
                    count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("longAdder"+ (end - start));

    }
}
