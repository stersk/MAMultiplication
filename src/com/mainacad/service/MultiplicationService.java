package com.mainacad.service;

import com.mainacad.model.MultiplicationResult;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplicationService {
  public static MultiplicationResult multiply(int a, int b) {
    int[] numbersA = getDigitsFromNumber(a);
    int[] numbersB = getDigitsFromNumber(b);
    
    int[][] multiplicationData = new int[numbersB.length][numbersA.length + numbersB.length + 2];
    for (int i = 0; i < multiplicationData.length; i++) {
      Arrays.fill(multiplicationData[i], -1);
    }

    for (int i = 0; i < numbersB.length; i++) {
      for (int j = numbersA.length - 1; j >= 0 ; j--) {
        multiplicationData[numbersB.length - i - 1][j + i + 1] = numbersB[i] * numbersA[j];
      }
      
      normalizeDigitsRow(multiplicationData[numbersB.length - i - 1]);
    }

    int[] resultDigits = getResultDigitsFromMultiplicationData(multiplicationData);
    int result = getNumberFromDigitsArray(resultDigits);

    MultiplicationResult resultData = new MultiplicationResult();
    resultData.setMultiplicationResult(result);
    resultData.setMultiplicationDescription(visualizeMultiplication(a, b, result, multiplicationData));

    return resultData;
  }

  private static int getNumberFromDigitsArray(int[] resultDigits) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < resultDigits.length; i++) {
      if (resultDigits[i] != -1) {
        stringBuilder.append(resultDigits[i]);
      }
    }

    return Integer.parseInt(stringBuilder.toString());
  }

  private static String visualizeMultiplication(int a, int b, int result, int[][] multiplicationData) {
    StringBuilder stringBuilder = new StringBuilder();
    int aDigitsCount = String.valueOf(a).length();
    int bDigitsCount = String.valueOf(b).length();

    int stringLength = aDigitsCount + bDigitsCount + 2;

    // number a
    List<String> rows = new ArrayList<>();
    rows.add("\n");
    rows.add(stringBuilder.append(StringUtils.leftPad(String.valueOf(a), stringLength)).toString());

    // number b
    stringBuilder.setLength(0);
    rows.add(stringBuilder.append("*").append(StringUtils.leftPad(String.valueOf(b), stringLength - 1)).toString());

    // first separator
    int separatorLength = 0;
    for (int i = 0; i < multiplicationData[0].length; i++) {
      if (multiplicationData[0][i] != -1) {
        separatorLength++;
      }
    }

    stringBuilder.setLength(0);
    rows.add(stringBuilder.append(StringUtils.leftPad(StringUtils.leftPad("", separatorLength, "-"), stringLength)).toString());

    // multiplication process lines
    for (int i = 0; i < multiplicationData.length; i++) {
      stringBuilder.setLength(0);
      for (int j = 0; j < multiplicationData[i].length - 2; j++) {
        if (multiplicationData[i][j] == -1) {
          stringBuilder.append(" ");
        } else {
          stringBuilder.append(multiplicationData[i][j]);
        }
      }

      String rowRepresentation = StringUtils.leftPad(stringBuilder.toString(), stringLength);

      if (i == 1) {
        stringBuilder = new StringBuilder(rowRepresentation);
        stringBuilder.setCharAt(0, '+');
        rowRepresentation = stringBuilder.toString();
      }

      rows.add(rowRepresentation);
    }

    // second separator
    stringBuilder.setLength(0);
    rows.add(stringBuilder.append(StringUtils.leftPad(StringUtils.leftPad("", String.valueOf(result).length(), "-"), stringLength)).toString());

    // multiplication result
    stringBuilder.setLength(0);
    rows.add(stringBuilder.append(StringUtils.leftPad(String.valueOf(result), stringLength)).toString());

    return StringUtils.join(rows, "\n");
  }

  private static int[] getResultDigitsFromMultiplicationData(int[][] multiplicationData) {
    int[] result = new int[multiplicationData[0].length - 2];

    for (int i = 0; i < multiplicationData[0].length - 2; i++) {
      for (int j = 0; j < multiplicationData.length; j++) {
        if (multiplicationData[j][i] != -1) {
          result[i] += multiplicationData[j][i];
        }
      }
    }

    normalizeDigitsRow(result);

    return result;
  }

  private static void normalizeDigitsRow(int[] multiplicationDatum) {

    for (int i = multiplicationDatum.length - 1; i > 0; i--) {
      if (multiplicationDatum[i] == -1 || multiplicationDatum[i] < 10) {
        continue;
      }

      if (multiplicationDatum[i - 1] == -1) {
        multiplicationDatum[i - 1] = multiplicationDatum[i] / 10;
      } else {
        multiplicationDatum[i - 1] += multiplicationDatum[i] / 10;
      }

      multiplicationDatum[i] = multiplicationDatum[i] % 10;
    }
  }

  private static int[] getDigitsFromNumber(int a) {
    char[] chars = Integer.toString(a).toCharArray();
    int[] digits = new int[chars.length];

    for (int i = 0; i < chars.length; i++) {
      digits[i] = Character.getNumericValue(chars[i]);
    }

    return digits;
  }
}
