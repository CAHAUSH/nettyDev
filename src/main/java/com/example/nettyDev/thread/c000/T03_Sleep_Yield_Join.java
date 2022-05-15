package com.example.nettyDev.thread.c000;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 13:56
 */
public class T03_Sleep_Yield_Join {

    public static void main(String[] args) {

    }
    static void testSleep(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    //休眠，把cpu让给其它线程来处理
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("a"+i);
                //当前线程退出，重新和其它线程竞争cpu
                //就是返回到就绪状态
                if(i%10==0) {
                    Thread.yield();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("a"+i);
                //当前线程退出，重新和其它线程竞争cpu
                if(i%10==0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin(){
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("a"+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            try {
                //立马去执行t1线程，等t2运行完之后，t1继续运行
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
