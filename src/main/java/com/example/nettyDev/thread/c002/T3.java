package com.example.nettyDev.thread.c002;

/**
 * @author SunHang
 * @description: 懒汉式单例，非线程安全
 * @date 2022/5/21 10:21
 */
public class T3 {
    private static T3 instance;
    static public T3 getInstance(){
        if(instance == null){
            instance = new T3();
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(T3.getInstance().hashCode());
            }).start();
        }
    }
}
