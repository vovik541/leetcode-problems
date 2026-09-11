package dev.vkh.solutions;

class Solution {
  public static int totalNumbers(int[] digits) {
    boolean[] seen = new boolean[1000];
    int distinctCount = 0;

    for (int firstIndex = 0; firstIndex < digits.length; firstIndex++) {
      if (digits[firstIndex] == 0) {
        continue;
      }

      for (int secondIndex = 0; secondIndex < digits.length; secondIndex++) {
        if (secondIndex == firstIndex) {
          continue;
        }

        for (int thirdIndex = 0; thirdIndex < digits.length; thirdIndex++) {
          if (thirdIndex == firstIndex || thirdIndex == secondIndex) {
            continue;
          }

          if ((digits[thirdIndex] & 1) == 1) {
            continue;
          }

          int number = digits[firstIndex] * 100 + digits[secondIndex] * 10 + digits[thirdIndex];

          if (!seen[number]) {
            seen[number] = true;
            distinctCount++;
          }
        }
      }
    }

    return distinctCount;
  }

  static void main() {
    System.out.println(totalNumbers(new int[] {1, 2, 3, 4})); // 12
    System.out.println(totalNumbers(new int[] {0, 2, 2})); // 2
    System.out.println(totalNumbers(new int[] {6, 6, 6})); // 1
    System.out.println(totalNumbers(new int[] {1, 3, 5})); // 0
  }
}
