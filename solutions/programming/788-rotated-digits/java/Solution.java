package dev.vkh.solutions;

class Solution {

  public int rotatedDigits(int n) {
    int goodNumbersCount = 0;

    for (int currentNumber = 1; currentNumber <= n; currentNumber++) {

      boolean hasAtLeastOneDifferentDigit = false;
      boolean isValidAfterRotation = true;

      int numberToCheck = currentNumber;

      while (numberToCheck > 0) {
        int currentDigit = numberToCheck % 10;
        numberToCheck /= 10;

        if (currentDigit == 2 || currentDigit == 5 || currentDigit == 6 || currentDigit == 9) {
          hasAtLeastOneDifferentDigit = true;
        }

        if (currentDigit == 3 || currentDigit == 4 || currentDigit == 7) {
          isValidAfterRotation = false;
          break;
        }
      }

      if (isValidAfterRotation && hasAtLeastOneDifferentDigit) {
        goodNumbersCount++;
      }
    }

    return goodNumbersCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.rotatedDigits(10)); // 4
    System.out.println(solution.rotatedDigits(1)); // 0
    System.out.println(solution.rotatedDigits(2)); // 1
  }
}
