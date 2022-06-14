package com.example.nettyDev.thread.c003_cas;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author SunHang
 * @description:  使用AtomicInteger来实现整数++的前程安全
 * @date 2022/5/28 10:21
 */
public class T2_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);

    /**
     * 多线程累加时，可以保证原子性，从而得到正确结果。不用使用锁即可实现
     * */
    public void m(){
        for (int i = 0; i < 10000; i++) {
            //count.getAndIncrement(); 相当于 count ++ ；
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T2_AtomicInteger t = new T2_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(()->{
                t.m();
            }));
        }
        threads.forEach((o)->{
            o.start();
        });

        /**
         * 在当前场景下，join的作用是让主线程最后执行
         * */
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }
}
