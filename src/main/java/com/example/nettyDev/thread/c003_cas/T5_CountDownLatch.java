package com.example.nettyDev.thread.c003_cas;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SunHang
 * @description:
 *
 * 一、CountDownLatch
 *
 *
 *
 * 二、CyclicBarrier
 * 场景-复杂操作：
 * 1数据库
 * 2网络
 * 3文件
 * 可以使用并发执行
 * 线程1-操作数据库
 * 线程2-操作网络
 * 线程3-操作文件
 * 且要三个线程都完成了，再执行某操作
 *
 * 另外，限流可以考虑使用 GUAVA RateLimiter
 *
 *
 * 三、
 *
 * @date 2022/5/28 11:41
 */
public class T5_CountDownLatch {
    //计数门栓
    static CountDownLatch latch = new CountDownLatch(50);

    //栅栏
    static CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
        @Override
        public void run() {
            System.out.println("满10个线程，执行一次当前方法");
        }
    });

    //平时用不到，阶段锁
    static Phaser phaser = new Phaser();

    /**
    *中文释义：信号标 acqui
     */
    static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        /*try {
            //阻塞...等待指定个线程执行完。
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /**barrier测试*/
        for (int i = 0; i < 19; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        //semaphore测试
        new Thread(()->{
            try {
                //（阻塞）获取信号，信号量-1后，继续往下执行。获取不到信号量（==0），进行阻塞等待
                semaphore.acquire();
                System.out.println("t1 running");
                Thread.sleep(200);
                System.out.println("t1 running end");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放信号，信号量+1
                semaphore.release();
            }
        }).start();

        new Thread(()->{
            try {
                //（阻塞）获取信号，信号量-1
                semaphore.acquire();
                System.out.println("t2 running");
                Thread.sleep(200);
                System.out.println("t2 running end");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放信号，信号量+1
                semaphore.release();
            }
        }).start();
    }
}
