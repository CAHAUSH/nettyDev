package com.example.nettyDev.thread.c002;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SunHang
 * @description: 锁的细化，synchronized的使用最小化（锁粗化和细化只是概念上的，使用时需要注意）
 * @date 2022/5/21 10:21
 */
public class T7 {
    int count = 0;


    /*synchronized*/ public void m(){
        /**
         * 此处可能有其他大量业务代码
         * synchronized可能会使程序出现非常多的小锁，比如：synchronized放在基线大的for循环中，那么此时不如把synchronized加到方法上（进行锁的粗化）
         */
        synchronized (this) {
            count++;
        }
    }

    public static void main(String[] args) {
        T7 t = new T7();
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
