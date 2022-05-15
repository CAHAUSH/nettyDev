package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 20:54
 */
public class T2 {
    private int count=10;
    //等价于synchronized（this）
    public synchronized void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }
}
