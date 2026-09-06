package dev.vkh.solutions;

class Solution {

  public static int numDistinct(String s, String t) {
    long[] dp = new long[t.length() + 1];
    dp[0] = 1;

    for (char sourceCharacter : s.toCharArray()) {
      for (int targetIndex = t.length() - 1; targetIndex >= 0; targetIndex--) {
        if (sourceCharacter == t.charAt(targetIndex)) {
          dp[targetIndex + 1] += dp[targetIndex];
        }
      }
    }

    return (int) dp[t.length()];
  }

  static void main() {
    System.out.println(numDistinct("rabbbit", "rabbit")); // 3
    System.out.println(numDistinct("babgbag", "bag")); // 5
    System.out.println(numDistinct("abc", "abc")); // 1
    System.out.println(numDistinct("abc", "abcd")); // 0
  }
}
