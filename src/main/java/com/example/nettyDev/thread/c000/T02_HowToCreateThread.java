package com.example.nettyDev.thread.c000;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 13:46
 */
public class T02_HowToCreateThread {
    static class MyTread extends Thread{
        @Override
        public void run() {
            System.out.println("hello my thread");
        }
    }
    static class MyRun implements Runnable{
        @Override
        public void run() {
            System.out.println("hello my run");
        }
    }

    public static void main(String[] args) {
        new MyTread().start();
        new Thread(new MyRun()).start();
        new Thread(()->{
            System.out.println("hello labmda");
        }).start();
    }
}
