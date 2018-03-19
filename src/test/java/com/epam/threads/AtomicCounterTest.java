package com.epam.threads;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AtomicCounterTest {

    private AtomicCounter atomicCounter;

    @Before
    public void init(){
        atomicCounter = new AtomicCounter();
    }

    @Test
    public void testAtomicCounterWorksProperly() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);
        ExecutorService service = Executors.newFixedThreadPool(1001);
        for(int i = 0; i < 1000; i++){
            service.submit(() -> {
                latch.countDown();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicCounter.incrementCounter();
            });
        }
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        assertEquals(1000, atomicCounter.getAmount().intValue());
    }
}
