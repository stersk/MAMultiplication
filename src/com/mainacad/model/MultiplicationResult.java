package com.mainacad.model;

public class MultiplicationResult {
  private int multiplicationResult;
  private String multiplicationDescription;

  public int getMultiplicationResult() {
    return multiplicationResult;
  }

  public void setMultiplicationResult(int multiplicationResult) {
    this.multiplicationResult = multiplicationResult;
  }

  public String getMultiplicationDescription() {
    return multiplicationDescription;
  }

  public void setMultiplicationDescription(String multiplicationDescription) {
    this.multiplicationDescription = multiplicationDescription;
  }

  public MultiplicationResult(int multiplicationResult, String multiplicationDescription) {
    this.multiplicationResult = multiplicationResult;
    this.multiplicationDescription = multiplicationDescription;
  }

  public MultiplicationResult() {
  }
}
