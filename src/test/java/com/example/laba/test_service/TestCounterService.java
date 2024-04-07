package com.example.laba.test_service;

import static org.junit.Assert.assertEquals;

import com.example.laba.service.CounterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class CounterServiceTest {

  @Test
  void testIncrementRequestCount() {

    int initialCount = CounterService.getRequestCount();

    CounterService.incrementRequestCount();

    int newCount = CounterService.getRequestCount();
    assertEquals(initialCount + 1, newCount);
  }

  @Test
  void testGetRequestCount() {
    int initialCount = CounterService.getRequestCount();
    CounterService.incrementRequestCount();
    CounterService.incrementRequestCount();

    int currentCount = CounterService.getRequestCount();

    assertEquals(initialCount + 2, currentCount);
  }


  @Test
  void testSingleton() {
    CounterService service1 = null;
    CounterService service2 = null;

    service1 = CounterService.getInstance();
    service2 = CounterService.getInstance();

    assertEquals(service1, service2);
  }
}
