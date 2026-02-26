package dev.vkh.solutions;

public class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numSteps("1101"));
    System.out.println(solution.numSteps("10"));
    System.out.println(solution.numSteps("1"));
  }

  public int numSteps(String binaryRepresentation) {
    int stepsCount = 0;
    int carry = 0;

    for (int index = binaryRepresentation.length() - 1; index > 0; index--) {
      int currentBit = binaryRepresentation.charAt(index) - '0';
      int currentValue = currentBit + carry;

      if (currentValue % 2 == 0) {
        stepsCount += 1;
      } else {
        stepsCount += 2;
        carry = 1;
      }
    }

    return stepsCount + carry;
  }
}
