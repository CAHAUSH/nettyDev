package com.example.nettyDev.thread.c001;

import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @description: 一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁
 *                 也就是说synchronized获得的锁是可重入的。
 * @date 2022/5/15 21:54
 */
public class T6_Sycn_Re_Entrant {
    synchronized void m1(){
        System.out.println("m1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end");
    }

    synchronized void m2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2");
    }

    public static void main(String[] args) {

    }
}
