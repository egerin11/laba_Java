package com.example.laba.test_service;

import static org.junit.Assert.assertEquals;

import com.example.laba.service.CounterService;
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
}
