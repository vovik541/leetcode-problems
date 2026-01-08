package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  static void main(String[] args) {
    Solution solution = new Solution();

    int[] nums1a = {2, 1, -2, 5};
    int[] nums2a = {3, 0, -6};
    System.out.println(solution.maxDotProduct(nums1a, nums2a));

    int[] nums1b = {3, -2};
    int[] nums2b = {2, -6, 7};
    System.out.println(solution.maxDotProduct(nums1b, nums2b));
  }

  public int maxDotProduct(int[] nums1, int[] nums2) {
    int firstLength = nums1.length;
    int secondLength = nums2.length;

    final int negativeInfinity = Integer.MIN_VALUE / 4;

    int[][] bestDotProduct = new int[firstLength + 1][secondLength + 1];
    for (int i = 0; i <= firstLength; i++) {
      Arrays.fill(bestDotProduct[i], negativeInfinity);
    }

    for (int i = 1; i <= firstLength; i++) {
      for (int j = 1; j <= secondLength; j++) {
        int pairProduct = nums1[i - 1] * nums2[j - 1];

        int skipFromFirst = bestDotProduct[i - 1][j];
        int skipFromSecond = bestDotProduct[i][j - 1];

        int startNewSubsequence = pairProduct;

        int extendPreviousSubsequence = bestDotProduct[i - 1][j - 1];
        if (extendPreviousSubsequence != negativeInfinity) {
          extendPreviousSubsequence += pairProduct;
        } else {
          extendPreviousSubsequence = negativeInfinity;
        }

        bestDotProduct[i][j] =
            maxOfFour(
                skipFromFirst, skipFromSecond, startNewSubsequence, extendPreviousSubsequence);
      }
    }

    return bestDotProduct[firstLength][secondLength];
  }

  private int maxOfFour(int a, int b, int c, int d) {
    return Math.max(Math.max(a, b), Math.max(c, d));
  }
}
