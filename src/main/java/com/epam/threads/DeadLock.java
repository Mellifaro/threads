package com.epam.threads;

public class DeadLock {

    public static void main(String[] args) throws InterruptedException {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        new Thread(new Runnable() {
            public void run() {
                synchronized (lock2){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName() + " is running");
                    }
                }
            }
        }).start();

        synchronized (lock1){
            Thread.sleep(100);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        }
    }

}
