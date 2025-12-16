package com.miranda.inventory_service;

import com.miranda.inventory_service.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class InventoryConcurrencyTest {

    @Autowired
    InventoryService service;

    @Test
    void shouldShowRaceCondition() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                try {
                    service.reserve(1);
                } catch (Exception ignored) {
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
    }
}
