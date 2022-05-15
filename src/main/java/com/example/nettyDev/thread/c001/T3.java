package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 20:57
 */
public class T3 {
    private static int count=10;
    //静态方法加synchronied等价于，synchronized（T3.class）
    public synchronized static void m(){
        count--;
        System.out.println(Thread.currentThread().getName()+"count="+count);
    }
}
