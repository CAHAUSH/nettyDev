package com.example.nettyDev.thread.c000;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 14:10
 */
public class T04_ThreadTest {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        System.out.println(myThread.getState());
        myThread.start();
        myThread.join();
        System.out.println(myThread.getState());
    }
}
