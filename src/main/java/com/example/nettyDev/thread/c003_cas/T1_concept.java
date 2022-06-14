package com.example.nettyDev.thread.c003_cas;

/**
 * @author SunHang
 * @description:
 *  CAS （无锁优化  自旋锁）
 *  Compare adn Swap
 *  cas(V, Expected, NewValue)
 *  - if V ==  E
 *      V=NewValue
 *    otherwise
 *      try it again / fail
 *
 *  - CPU 原语支持
 *
 *  会存在ABA问题， 一个线程把值 从A改成B ，再从B改为A,
 *  - 可以加version
 *  - A 1.0
 *  - B 2.0
 *  - A 3.0
 *  - cas (version)  提供了类AtomicStampedReference
 *  如果操作的是基础类型，对最终结果没有影响。如果是引用类型，则无法保证是否有影响
 *  基于unsafe 类提供的 compareAndSwapInt
 *
 * @date 2022/5/28 9:50
 */
public class T1_concept {
}
