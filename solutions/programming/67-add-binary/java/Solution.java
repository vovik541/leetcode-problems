package dev.vkh.solutions;

public class Solution {

  public String addBinary(String firstBinary, String secondBinary) {
    StringBuilder resultBuilder = new StringBuilder();

    int firstIndex = firstBinary.length() - 1;
    int secondIndex = secondBinary.length() - 1;
    int carry = 0;

    while (firstIndex >= 0 || secondIndex >= 0 || carry != 0) {
      int currentSum = carry;

      if (firstIndex >= 0) {
        currentSum += firstBinary.charAt(firstIndex) - '0';
        firstIndex--;
      }

      if (secondIndex >= 0) {
        currentSum += secondBinary.charAt(secondIndex) - '0';
        secondIndex--;
      }

      resultBuilder.append(currentSum % 2);
      carry = currentSum / 2;
    }

    return resultBuilder.reverse().toString();
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.addBinary("1101", "1"));
  }
}
