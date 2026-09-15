package dev.vkh.solutions;

class Solution {

  public static int maxPalindromes(String s, int k) {
    int length = s.length();
    boolean[][] isPalindrome = new boolean[length][length];

    for (int leftIndex = length - 1; leftIndex >= 0; leftIndex--) {
      for (int rightIndex = leftIndex; rightIndex < length; rightIndex++) {
        isPalindrome[leftIndex][rightIndex] =
            s.charAt(leftIndex) == s.charAt(rightIndex)
                && (rightIndex - leftIndex <= 1 || isPalindrome[leftIndex + 1][rightIndex - 1]);
      }
    }

    int[] dp = new int[length + 1];

    for (int endIndex = 1; endIndex <= length; endIndex++) {
      dp[endIndex] = dp[endIndex - 1];

      for (int startIndex = 0; startIndex + k <= endIndex; startIndex++) {
        if (isPalindrome[startIndex][endIndex - 1]) {
          dp[endIndex] = Math.max(dp[endIndex], dp[startIndex] + 1);
        }
      }
    }

    return dp[length];
  }

  static void main() {
    System.out.println(maxPalindromes("abaccdbbd", 3)); // 2
    System.out.println(maxPalindromes("adbcda", 2)); // 0
    System.out.println(maxPalindromes("aaaaa", 2)); // 2
    System.out.println(maxPalindromes("abcdef", 1)); // 6
  }
}
