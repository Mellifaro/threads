package com.epam.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter {
    private AtomicLong amount = new AtomicLong(0);

    public void incrementCounter(){
        amount.incrementAndGet();
    }

    public AtomicLong getAmount() {
        return amount;
    }

    public void setAmount(AtomicLong amount) {
        this.amount = amount;
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicCounter atomicCounter = new AtomicCounter();
        ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i = 0; i < 1000; i++){
            service.submit(atomicCounter::incrementCounter);
        }
        Thread.sleep(500);
        System.out.println(atomicCounter.amount.get());
    }


}
