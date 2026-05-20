package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int[] findThePrefixCommonArray(int[] A, int[] B) {
    int arrayLength = A.length;
    int[] frequencyByValue = new int[arrayLength + 1];
    int[] prefixCommonArray = new int[arrayLength];

    int commonValuesCount = 0;

    for (int index = 0; index < arrayLength; index++) {
      frequencyByValue[A[index]]++;
      if (frequencyByValue[A[index]] == 2) {
        commonValuesCount++;
      }

      frequencyByValue[B[index]]++;
      if (frequencyByValue[B[index]] == 2) {
        commonValuesCount++;
      }

      prefixCommonArray[index] = commonValuesCount;
    }

    return prefixCommonArray;
  }

  static void main() {
    System.out.println(
        Arrays.toString(
            new Solution()
                .findThePrefixCommonArray(
                    new int[] {1, 3, 2, 4}, new int[] {3, 1, 2, 4}))); // [0, 2, 3, 4]
  }
}
