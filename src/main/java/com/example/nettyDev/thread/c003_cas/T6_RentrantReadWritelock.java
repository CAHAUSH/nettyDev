package com.example.nettyDev.thread.c003_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author SunHang
 * @description: ReentrantReadWriteLock
 * 共享锁 读锁，允许其他读锁进入，不允许写锁进入
 * 排它锁 写锁，不允许读锁、写锁进入
 *
 * @date 2022/5/28 11:41
 */
public class T6_RentrantReadWritelock {
    static ReentrantLock lock = new ReentrantLock();
    private static int value;
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock,int v){
        try {
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            new Thread(()->{read(lock);}).start();
        }*/

        for (int i = 0; i < 10; i++) {
            new Thread(()->{read(readLock);}).start();
        }

        /*new Thread(()->{write(lock,1);}).start();*/
        for (int i = 0; i < 2; i++) {
            new Thread(()->{write(writeLock,1);}).start();
        }
    }
}
