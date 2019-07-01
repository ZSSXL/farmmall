package com.fmall.onenet.mq;

import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
    private static LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(500);

    public static synchronized void  put(Object o){
        try {
            linkedBlockingQueue.put(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static synchronized Object take(){
        try {
            return linkedBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Exception();
    }

    public static synchronized boolean isEmpty(){
        return linkedBlockingQueue.isEmpty();
    }
}
