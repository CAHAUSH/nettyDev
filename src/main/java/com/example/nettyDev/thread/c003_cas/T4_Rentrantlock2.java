package com.example.nettyDev.thread.c003_cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SunHang
 * @description: ReentrantLock 提供sync之外的功能，如：trylock
 * ReentrantLock  VS  synchronized
 * cas  vs  sync
 * trylock
 * lockinterupptibly
 * 公平锁和非公平锁切换   vs    只有非公平锁
 *
 * @date 2022/5/28 11:41
 */
public class T4_Rentrantlock2 {
    ReentrantLock lock = new ReentrantLock();
    void m1(){
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }

            } catch (InterruptedException e) {
                    e.printStackTrace();
          }finally {
            lock.unlock();
        }
    }

    void m2(){
        boolean locked = false;
        try {
            locked = lock.tryLock(Long.parseLong("5"), TimeUnit.SECONDS);
            System.out.println("m2"+locked);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(locked){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T4_Rentrantlock2 t = new T4_Rentrantlock2();
        new Thread(()->{t.m1();}).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{t.m2();}).start();
    }
}
