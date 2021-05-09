package org.arsonal.test;

import org.junit.jupiter.api.Test;

public class Tester {

    public static void main(String[] args) {
        Object LOCK = new Object();
        new Thread(() -> foo(LOCK)).start();

        try {
            synchronized (LOCK) {
                System.out.println(Thread.currentThread().getName() + " 即将处于等待状态！");
                LOCK.wait();
                System.out.println(Thread.currentThread().getName() + ": 我被唤醒了！！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void foo(Object lock) {
        synchronized (lock) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.notifyAll();
            System.out.println(Thread.currentThread().getName() + " 唤醒所以等待的线程");
        }
    }
}
