package dev.vkh.solutions;

class Solution {
  public boolean rotateString(String s, String goal) {
    if (s.length() != goal.length()) {
      return false;
    }

    String doubledString = s + s;

    return doubledString.contains(goal);
  }

  static void main() {
    System.out.println(new Solution().rotateString("abcde", "cdeab"));
  }
}
