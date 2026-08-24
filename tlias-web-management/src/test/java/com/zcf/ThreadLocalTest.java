package com.zcf;

public class ThreadLocalTest {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("Main Message");

        //创建一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("Sub Message");
                System.out.println(Thread.currentThread().getName()+":"+ threadLocal.get());
            }
        }).start();

        System.out.println(Thread.currentThread().getName()+":"+ threadLocal.get());

        threadLocal.remove();

        System.out.println(Thread.currentThread().getName()+":"+ threadLocal.get());
    }
}
