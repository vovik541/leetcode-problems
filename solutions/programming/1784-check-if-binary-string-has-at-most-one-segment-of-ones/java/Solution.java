package dev.vkh.solutions;

class Solution {

  public boolean checkOnesSegment(String s) {
    return !s.contains("01");
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.checkOnesSegment("1001")); // false
    System.out.println(solution.checkOnesSegment("110")); // true
  }
}
