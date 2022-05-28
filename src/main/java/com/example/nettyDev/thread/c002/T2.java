package com.example.nettyDev.thread.c002;

/**
 * @author SunHang
 * @description: 饿汉式单例，简单实用，推荐使用。 缺点：不管实际是否会用到，类装载时就会实力化
 * @date 2022/5/21 10:18
 */
public class T2 {
    private static T2 instance = new T2();
    static public T2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        T2 t0 = T2.getInstance();
        T2 t1 = T2.getInstance();
        System.out.println(t0 == t1);
    }
}
