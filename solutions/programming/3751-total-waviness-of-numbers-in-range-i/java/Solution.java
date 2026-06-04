package dev.vkh.solutions;

class Solution {

  public int totalWaviness(int num1, int num2) {
    int totalWaviness = 0;

    for (int currentNumber = num1; currentNumber <= num2; currentNumber++) {
      totalWaviness += calculateWaviness(currentNumber);
    }

    return totalWaviness;
  }

  private int calculateWaviness(int number) {
    String numberText = String.valueOf(number);

    if (numberText.length() < 3) {
      return 0;
    }

    int waviness = 0;

    for (int index = 1; index < numberText.length() - 1; index++) {
      int previousDigit = numberText.charAt(index - 1) - '0';
      int currentDigit = numberText.charAt(index) - '0';
      int nextDigit = numberText.charAt(index + 1) - '0';

      if (currentDigit > previousDigit && currentDigit > nextDigit) {
        waviness++;
      } else if (currentDigit < previousDigit && currentDigit < nextDigit) {
        waviness++;
      }
    }

    return waviness;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.totalWaviness(120, 130)); // 3
    System.out.println(solution.totalWaviness(198, 202)); // 3
  }
}
