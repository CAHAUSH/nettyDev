package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 20:53
 */
public class T1 {
    private int count=10;
    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
