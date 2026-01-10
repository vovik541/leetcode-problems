package dev.vkh.solutions;

public class Solution {
  public int minimumDeleteSum(String s1, String s2) {
    int firstLength = s1.length();
    int secondLength = s2.length();

    int[] dp = new int[secondLength + 1];

    for (int j = secondLength - 1; j >= 0; j--) {
      dp[j] = dp[j + 1] + s2.charAt(j);
    }

    for (int i = firstLength - 1; i >= 0; i--) {
      int diagonalPrevious = dp[secondLength];
      dp[secondLength] = dp[secondLength] + s1.charAt(i);

      for (int j = secondLength - 1; j >= 0; j--) {
        int dpBelow = dp[j];
        char firstChar = s1.charAt(i);
        char secondChar = s2.charAt(j);

        if (firstChar == secondChar) {
          dp[j] = diagonalPrevious;
        } else {
          int deleteFromFirst = firstChar + dpBelow;
          int deleteFromSecond = secondChar + dp[j + 1];
          dp[j] = Math.min(deleteFromFirst, deleteFromSecond);
        }

        diagonalPrevious = dpBelow;
      }
    }

    return dp[0];
  }

  static void main(String[] args) {
    Solution solution = new Solution();

    System.out.println(solution.minimumDeleteSum("sea", "eat"));
    // 231
    System.out.println(solution.minimumDeleteSum("delete", "leet"));
    // 403
    System.out.println(solution.minimumDeleteSum("a", "a"));
    // 0
    System.out.println(solution.minimumDeleteSum("a", "b"));
    // 195
    System.out.println(solution.minimumDeleteSum("", "abc"));
    // 294
  }
}
