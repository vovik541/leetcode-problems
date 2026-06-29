package dev.vkh.solutions;

public class Solution {
  public int numOfStrings(String[] patterns, String word) {
    int count = 0;

    for (String pattern : patterns) {
      if (word.contains(pattern)) {
        count++;
      }
    }
    return count;
  }

  void main() {
    System.out.println(
        new Solution().numOfStrings(new String[] {"a", "abc", "bc", "d"}, "abc")); // 3
  }
}
