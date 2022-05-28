package com.example.nettyDev.thread.c002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SunHang
 * @description: 懒汉式单例，双检锁，volatile无法保证原子性
 * @date 2022/5/21 10:21
 */
public class T6_Atomicity {
    volatile int count = 0;

    /**
     * 在不加synchronized的时候，始终无法得到目标值：100000
     * */
    /*synchronized*/ public void m(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        T6_Atomicity t = new T6_Atomicity();
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
