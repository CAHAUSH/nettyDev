package com.example.nettyDev.thread.c002;

/**
 * @author SunHang
 * @description: 懒汉式单例，双检锁（为了给更少的代码加锁）
 * @date 2022/5/21 10:21
 */
public class T5 {
    private /*volatile*/ static T5 instance;
    static public T5 getInstance(){
        if(instance == null){
            synchronized (T5.class) {
                if(instance == null) {
                    /**
                     * 1开辟堆空间初始值、2堆空间赋实际值、3栈空间指向堆空间
                     * 指令重排可能导致：2、3两步调换，当前线程里对象创建一半的时候，另外一个线程拿到了当前对象，但是没有读到实际的值。
                     * */
                    instance = new T5();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(T5.getInstance().hashCode());
            }).start();
        }
    }
}
