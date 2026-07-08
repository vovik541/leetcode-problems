package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  private static final long MOD = 1_000_000_007L;

  public static int[] sumAndMultiply(String s, int[][] queries) {
    List<Integer> nonZeroPositions = new ArrayList<>();
    List<Integer> nonZeroDigits = new ArrayList<>();

    for (int index = 0; index < s.length(); index++) {
      int digit = s.charAt(index) - '0';

      if (digit != 0) {
        nonZeroPositions.add(index);
        nonZeroDigits.add(digit);
      }
    }

    int nonZeroCount = nonZeroDigits.size();

    long[] powerOfTen = new long[nonZeroCount + 1];
    long[] prefixNumber = new long[nonZeroCount + 1];
    long[] prefixDigitSum = new long[nonZeroCount + 1];

    powerOfTen[0] = 1;

    for (int index = 0; index < nonZeroCount; index++) {
      powerOfTen[index + 1] = (powerOfTen[index] * 10) % MOD;

      prefixNumber[index + 1] = (prefixNumber[index] * 10 + nonZeroDigits.get(index)) % MOD;

      prefixDigitSum[index + 1] = prefixDigitSum[index] + nonZeroDigits.get(index);
    }

    int[] answers = new int[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int left = queries[queryIndex][0];
      int right = queries[queryIndex][1];

      int leftCompressedIndex = lowerBound(nonZeroPositions, left);
      int rightCompressedIndexExclusive = upperBound(nonZeroPositions, right);

      if (leftCompressedIndex >= rightCompressedIndexExclusive) {
        answers[queryIndex] = 0;
        continue;
      }

      int length = rightCompressedIndexExclusive - leftCompressedIndex;

      long x =
          (prefixNumber[rightCompressedIndexExclusive]
                  - prefixNumber[leftCompressedIndex] * powerOfTen[length])
              % MOD;

      if (x < 0) {
        x += MOD;
      }

      long digitSum =
          prefixDigitSum[rightCompressedIndexExclusive] - prefixDigitSum[leftCompressedIndex];

      answers[queryIndex] = (int) ((x * digitSum) % MOD);
    }

    return answers;
  }

  private static int lowerBound(List<Integer> values, int target) {
    int left = 0;
    int right = values.size();

    while (left < right) {
      int middle = left + (right - left) / 2;

      if (values.get(middle) >= target) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }

  private static int upperBound(List<Integer> values, int target) {
    int left = 0;
    int right = values.size();

    while (left < right) {
      int middle = left + (right - left) / 2;

      if (values.get(middle) > target) {
        right = middle;
      } else {
        left = middle + 1;
      }
    }

    return left;
  }

  static void main() {
    String s1 = "10203004";
    int[][] queries1 = {
      {0, 7},
      {1, 3},
      {4, 6}
    };
    System.out.println(Arrays.toString(sumAndMultiply(s1, queries1))); // [12340, 4, 9]

    String s2 = "1000";
    int[][] queries2 = {
      {0, 3},
      {1, 1}
    };
    System.out.println(Arrays.toString(sumAndMultiply(s2, queries2))); // [1, 0]
  }
}
