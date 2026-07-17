package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static int[] gcdValues(int[] nums, long[] queries) {
    int maxValue = 0;

    for (int num : nums) {
      maxValue = Math.max(maxValue, num);
    }

    int[] frequency = new int[maxValue + 1];

    for (int num : nums) {
      frequency[num]++;
    }

    long[] pairsWithGcd = new long[maxValue + 1];

    for (int gcd = maxValue; gcd >= 1; gcd--) {
      long divisibleCount = 0;

      for (int multiple = gcd; multiple <= maxValue; multiple += gcd) {
        divisibleCount += frequency[multiple];
      }

      long pairCount = divisibleCount * (divisibleCount - 1) / 2;

      for (int multiple = gcd * 2; multiple <= maxValue; multiple += gcd) {
        pairCount -= pairsWithGcd[multiple];
      }

      pairsWithGcd[gcd] = pairCount;
    }

    long[] prefixPairs = new long[maxValue + 1];

    for (int gcd = 1; gcd <= maxValue; gcd++) {
      prefixPairs[gcd] = prefixPairs[gcd - 1] + pairsWithGcd[gcd];
    }

    int[] answer = new int[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      long query = queries[queryIndex];

      answer[queryIndex] = findFirstGcdWithEnoughPairs(prefixPairs, query + 1);
    }

    return answer;
  }

  private static int findFirstGcdWithEnoughPairs(long[] prefixPairs, long targetCount) {
    int left = 1;
    int right = prefixPairs.length - 1;
    int answer = right;

    while (left <= right) {
      int middle = left + (right - left) / 2;

      if (prefixPairs[middle] >= targetCount) {
        answer = middle;
        right = middle - 1;
      } else {
        left = middle + 1;
      }
    }

    return answer;
  }

  static void main() {
    System.out.println(
        Arrays.toString(gcdValues(new int[] {2, 3, 4}, new long[] {0, 2, 2}))); // Output: [1, 2, 2]
    System.out.println(
        Arrays.toString(
            gcdValues(new int[] {4, 4, 2, 1}, new long[] {5, 3, 1, 0}))); // Output: [4, 2, 1, 1]
    System.out.println(
        Arrays.toString(gcdValues(new int[] {2, 2}, new long[] {0, 0}))); // Output: [2, 2]
  }
}
