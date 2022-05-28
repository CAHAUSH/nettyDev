package com.example.nettyDev.thread.c002;

/**
 * @author SunHang
 * @description: 懒汉式单例，线程安全
 * @date 2022/5/21 10:21
 */
public class T4 {
    private static T4 instance;
    synchronized static public T4 getInstance(){
        if(instance == null){
            instance = new T4();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(T4.getInstance().hashCode());
            }).start();
        }
    }
}
