package com.mainacad.service;

import com.mainacad.model.MultiplicationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationServiceTest {

  @Test
  void testMultiply() {
    MultiplicationResult result = MultiplicationService.multiply(32, 68);

    assertNotNull(result);
    assertEquals(32*68, result.getMultiplicationResult());

    result = MultiplicationService.multiply(326, 168);
    assertNotNull(result);
    assertEquals(326*168, result.getMultiplicationResult());

    result = MultiplicationService.multiply(1326, 168);
    assertNotNull(result);
    assertEquals(1326*168, result.getMultiplicationResult());
  }
}