package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[] exampleOne = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    System.out.println(Arrays.toString(solution.sortByBits(exampleOne)));

    int[] exampleTwo = {1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
    System.out.println(Arrays.toString(solution.sortByBits(exampleTwo)));
  }

  public int[] sortByBits(int[] arr) {
    Integer[] boxedArray = Arrays.stream(arr).boxed().toArray(Integer[]::new);

    Arrays.sort(
        boxedArray,
        (firstValue, secondValue) -> {
          int firstValueBitCount = Integer.bitCount(firstValue);
          int secondValueBitCount = Integer.bitCount(secondValue);

          if (firstValueBitCount == secondValueBitCount) {
            return Integer.compare(firstValue, secondValue);
          }

          return Integer.compare(firstValueBitCount, secondValueBitCount);
        });

    for (int index = 0; index < arr.length; index++) {
      arr[index] = boxedArray[index];
    }

    return arr;
  }
}
