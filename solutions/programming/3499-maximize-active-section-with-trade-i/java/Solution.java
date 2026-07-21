package dev.vkh.solutions;

public class Solution {

  public int maxActiveSectionsAfterTrade(String s) {
    int originalOnes = 0;

    for (char currentChar : s.toCharArray()) {
      if (currentChar == '1') {
        originalOnes++;
      }
    }

    String augmented = "1" + s + "1";

    int maxGain = 0;
    int index = 0;

    while (index < augmented.length()) {
      if (augmented.charAt(index) == '0') {
        index++;
        continue;
      }

      int onesStart = index;

      while (index < augmented.length() && augmented.charAt(index) == '1') {
        index++;
      }

      int onesEnd = index - 1;

      if (onesStart == 0 || onesEnd == augmented.length() - 1) {
        continue;
      }

      if (augmented.charAt(onesStart - 1) != '0' || augmented.charAt(onesEnd + 1) != '0') {
        continue;
      }

      int leftZeroCount = countZerosToLeft(augmented, onesStart - 1);
      int rightZeroCount = countZerosToRight(augmented, onesEnd + 1);

      maxGain = Math.max(maxGain, leftZeroCount + rightZeroCount);
    }

    return originalOnes + maxGain;
  }

  private int countZerosToLeft(String s, int index) {
    int count = 0;

    while (index >= 0 && s.charAt(index) == '0') {
      count++;
      index--;
    }

    return count;
  }

  private int countZerosToRight(String s, int index) {
    int count = 0;

    while (index < s.length() && s.charAt(index) == '0') {
      count++;
      index++;
    }

    return count;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxActiveSectionsAfterTrade("01")); // Output: 1
    System.out.println(solution.maxActiveSectionsAfterTrade("0100")); // Output: 4
    System.out.println(solution.maxActiveSectionsAfterTrade("1000100")); // Output: 7
    System.out.println(solution.maxActiveSectionsAfterTrade("01010")); // Output: 4
  }
}
