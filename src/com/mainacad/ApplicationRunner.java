package com.mainacad;

import com.mainacad.model.MultiplicationResult;
import com.mainacad.service.MultiplicationService;

import java.util.logging.Logger;

public class ApplicationRunner {
  private static Logger logger = Logger.getLogger(ApplicationRunner.class.getName());

  public static void main(String[] args) {

    MultiplicationResult result = MultiplicationService.multiply(1326, 168);
    logger.info(result.getMultiplicationDescription());
  }
}
