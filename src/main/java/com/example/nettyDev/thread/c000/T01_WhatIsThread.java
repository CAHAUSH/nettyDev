package com.example.nettyDev.thread.c000;

import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 13:38
 */
public class T01_WhatIsThread {
    public static class T1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        //方法调用
        // new T1().run();
        //线程调用
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
