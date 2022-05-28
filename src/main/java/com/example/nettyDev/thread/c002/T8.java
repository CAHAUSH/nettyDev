package com.example.nettyDev.thread.c002;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @description: 锁定对象o，如果o的属性发生改变，不影响锁的使用
 *               但是如果o变成了另外一个对象，则锁定的对象发生改变
 *               应该避免锁定的对象引用，变成另一个对象
 * @date 2022/5/21 10:21
 */
public class T8 {
    /*final */Object o = new Object();


    void m(){
        synchronized (o){
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T8 t = new T8();
        //启动第一个线程
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(t::m, "t2");
        t.o=new Object();
        t2.start();

    }
}
