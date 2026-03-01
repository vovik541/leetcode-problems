package dev.vkh.solutions;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minPartitions("32"));                   // 3
    System.out.println(solution.minPartitions("82734"));                // 8
    System.out.println(solution.minPartitions("27346209830709182346")); // 9
  }

  public int minPartitions(String n) {
    int maximumDigit = 0;

    for (int index = 0; index < n.length(); index++) {
      int currentDigit = n.charAt(index) - '0';
      if (currentDigit > maximumDigit) {
        maximumDigit = currentDigit;
        if (maximumDigit == 9) {
          return 9;
        }
      }
    }

    return maximumDigit;
  }
}
