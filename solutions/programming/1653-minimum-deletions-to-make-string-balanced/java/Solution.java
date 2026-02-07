package dev.vkh.solutions;

public class Solution {
  public int minimumDeletions(String s) {
    int length = s.length();
    int[] prefixBCount = new int[length + 1];
    int[] suffixACount = new int[length + 1];

    for (int index = 0; index < length; index++) {
      prefixBCount[index + 1] = prefixBCount[index] + (s.charAt(index) == 'b' ? 1 : 0);
    }

    for (int index = length - 1; index >= 0; index--) {
      suffixACount[index] = suffixACount[index + 1] + (s.charAt(index) == 'a' ? 1 : 0);
    }

    int minimumDeletions = Integer.MAX_VALUE;
    for (int splitIndex = 0; splitIndex <= length; splitIndex++) {
      int deletionsIfSplitHere = prefixBCount[splitIndex] + suffixACount[splitIndex];
      minimumDeletions = Math.min(minimumDeletions, deletionsIfSplitHere);
    }

    return minimumDeletions;
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.minimumDeletions("aababbab"));
    System.out.println(solution.minimumDeletions("bbaaaaabb"));
  }
}
