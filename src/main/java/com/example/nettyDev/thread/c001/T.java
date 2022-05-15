package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 20:49
 */

/**
 * synchronized关键字
 * */
public class T {
    private int count = 10;
    private Object o = new Object();
    public void m(){
        /**
         * 此处锁定的是o对象，任何线程要执行以下代码，必须先拿到o的锁
         * */
        synchronized (o){
            count--;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
