package com.example.nettyDev.thread.c001;

/**
 * @author SunHang
 * @description: TODO
 * @date 2022/5/15 22:11
 */
public class T8_Sycn_Theory {
    /**
     * synchronized的底层实现
     * JDK早期的，重量级锁 - OS（需要去操作系统去申请）
     *
     * 后来的改进
     *
     * 锁升级的概念：
     *      马士兵博客：我就是厕所所长
     *
     *  synchronized (object)  不能锁String Integer Long等
     *  实现：给被加锁的对象头增加一个markword标记
     *  锁升级
     *  markword 记录这个线程的ID （只有一个线程获取当前对象的时候，不会加锁只是记录这个ID 称为偏向锁；效率较高）
     *  如果有线程争用：升级为自旋锁（占用cpu但是不访问操作系统，在用户态来解决锁的问题，不经过内核态所以效率更高 ）
     *  大概10次以后，再次进行锁升级
     *  升级为重量级锁 -OS
     *  选用：
     *  加锁代码执行时间短、线程数少  选用自旋锁
     *  加锁代码执行时间长、鲜橙多    选用重量级锁（系统锁synchronized）
     *
     *
     *
     *
     *
     *
     *
     *
     * */
}
