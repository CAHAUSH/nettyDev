package com.example.nettyDev.thread.c001;

import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @description:
 * 程序再执行过程中，如果出现异常，默认情况下锁会被释放
 * 所以，在并发处理过程中，有异常要多加小心，不然可能会发生不一致的情况
 *
 * @date 2022/5/15 22:00
 */
public class T7_Sync_Exception {
    int count=10;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+" start" );
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count="+count );
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count==5){
                //此处抛出异常，锁被释放。要想不释放，可以在这里try catch,然后让循环继续
                int i = count/0;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T7_Sync_Exception t = new T7_Sync_Exception();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r,"t").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r,"t2").start();
    }
}
