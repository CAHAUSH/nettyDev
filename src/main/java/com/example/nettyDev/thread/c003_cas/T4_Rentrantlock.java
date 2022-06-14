package com.example.nettyDev.thread.c003_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SunHang
 * @description: ReentrantLock（手动解锁）可以替换掉synchronized （自动解锁）
 * @date 2022/5/28 11:41
 */
public class T4_Rentrantlock {
    /**
    *默认是非公平锁
    * 等待线程会在队列里进行排队，此时，又新建了一个线程，是否是公平锁，取决于新建线程是否进入等待队列，还是直接竞争锁
     */
    ReentrantLock lock = new ReentrantLock();
    void m1(){
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }

            } catch (InterruptedException e) {
                    e.printStackTrace();
          }finally {
            lock.unlock();
        }
    }

    void m2(){
        lock.lock();
        try {
            System.out.println("m2");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T4_Rentrantlock t = new T4_Rentrantlock();
        new Thread(()->{t.m1();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{t.m2();}).start();
    }
}
