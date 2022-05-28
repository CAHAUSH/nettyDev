package com.example.nettyDev.thread.c002;

import java.util.concurrent.TimeUnit;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/21 9:06
 */
public class T1_Volatile {
    /**
     * 比较有无volatile，程序执行差异。
     * */
    /*volatile*/ boolean running = true;
    int i =0;
    void m(){
        System.out.println("start");
        while (running){
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            System.out.println(i+":"+running);
            i++;
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        T1_Volatile t=new T1_Volatile();
        new Thread(t::m,"t").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //当前线程修改完runnig的值后，不确定什么时候才能被另外一个线程读到数据
        t.running = false;
        System.out.println("main thread:"+t.running);
    }
}
