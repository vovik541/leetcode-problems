package dev.vkh.solutions;

import java.util.LinkedList;
import java.util.List;

class Solution {

  public static List<Integer> sequentialDigits(int low, int high) {
    List<Integer> result = new LinkedList<>();

    int digitsCount = 0;
    int number = low;
    int startingDigit = -1;

    while (number != 0) {
      if (number < 10) {
        startingDigit = number;
      }
      number /= 10;
      digitsCount++;
    }

    while (number <= high) {
      if (9 - digitsCount >= 0 && 10 - startingDigit >= digitsCount) {
        number = buildNumber(startingDigit, digitsCount);
        if (number >= low && number <= high) {
          result.add(number);
        }
        startingDigit++;
      } else {
        if (startingDigit > 9 || digitsCount > 9) {
          break;
        }
        startingDigit = 1;
        digitsCount++;
      }
    }

    return result;
  }

  private static int buildNumber(int startingDigit, int digitCount) {
    int number = startingDigit;

    for (int i = 1; i < digitCount; i++) {
      startingDigit++;
      number = number * 10 + startingDigit;
    }

    return number;
  }

  static void main() {
    System.out.println(sequentialDigits(100, 300)); // [123,234]
    System.out.println(sequentialDigits(1000, 13000)); // [1234,2345,3456,4567,5678,6789,12345]
  }
}
