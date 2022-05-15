package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 21:05
 */
public class T4 implements Runnable{
    private /*volatile*/ int count=100;

    @Override
    /**无锁会重复输出*/
    public /* synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }

    public static void main(String[] args) {
        T4 t = new T4();
        for (int i = 0; i < 100; i++) {
            new Thread(t,"Thread"+i).start();
        }
    }
}
