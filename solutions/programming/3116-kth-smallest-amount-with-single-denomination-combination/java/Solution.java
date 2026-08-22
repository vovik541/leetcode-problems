package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

  public static long findKthSmallest(int[] coins, int k) {
    int[] usefulCoins = removeRedundantCoins(coins);

    long leftAmount = 1L;
    long rightAmount = (long) usefulCoins[0] * k;

    long[] subsetLcms = buildSubsetLcms(usefulCoins, rightAmount);

    while (leftAmount < rightAmount) {
      long middleAmount = leftAmount + (rightAmount - leftAmount) / 2;

      if (countPossibleAmounts(middleAmount, subsetLcms) >= k) {
        rightAmount = middleAmount;
      } else {
        leftAmount = middleAmount + 1;
      }
    }

    return leftAmount;
  }

  private static long countPossibleAmounts(long maximumAmount, long[] subsetLcms) {
    long possibleAmountsCount = 0L;

    for (int subsetMask = 1; subsetMask < subsetLcms.length; subsetMask++) {
      long subsetLcm = subsetLcms[subsetMask];

      if (subsetLcm > maximumAmount) {
        continue;
      }

      long divisibleAmountsCount = maximumAmount / subsetLcm;

      if ((Integer.bitCount(subsetMask) & 1) == 1) {
        possibleAmountsCount += divisibleAmountsCount;
      } else {
        possibleAmountsCount -= divisibleAmountsCount;
      }
    }

    return possibleAmountsCount;
  }

  private static long[] buildSubsetLcms(int[] coins, long maximumRelevantAmount) {
    int subsetCount = 1 << coins.length;
    long[] subsetLcms = new long[subsetCount];
    subsetLcms[0] = 1L;

    for (int subsetMask = 1; subsetMask < subsetCount; subsetMask++) {
      int lowestSetBit = subsetMask & -subsetMask;
      int coinIndex = Integer.numberOfTrailingZeros(lowestSetBit);
      int previousSubsetMask = subsetMask ^ lowestSetBit;

      subsetLcms[subsetMask] =
          calculateCappedLcm(
              subsetLcms[previousSubsetMask], coins[coinIndex], maximumRelevantAmount);
    }

    return subsetLcms;
  }

  private static long calculateCappedLcm(long firstValue, long secondValue, long limit) {
    if (firstValue > limit) {
      return limit + 1;
    }

    long greatestCommonDivisor = gcd(firstValue, secondValue);
    long reducedFirstValue = firstValue / greatestCommonDivisor;

    if (reducedFirstValue > limit / secondValue) {
      return limit + 1;
    }

    return reducedFirstValue * secondValue;
  }

  private static long gcd(long firstValue, long secondValue) {
    while (secondValue != 0) {
      long remainder = firstValue % secondValue;
      firstValue = secondValue;
      secondValue = remainder;
    }

    return firstValue;
  }

  private static int[] removeRedundantCoins(int[] coins) {
    Arrays.sort(coins);

    List<Integer> usefulCoins = new ArrayList<>();

    for (int coin : coins) {
      boolean isRedundant = false;

      for (int smallerCoin : usefulCoins) {
        if (coin % smallerCoin == 0) {
          isRedundant = true;
          break;
        }
      }

      if (!isRedundant) {
        usefulCoins.add(coin);
      }
    }

    int[] result = new int[usefulCoins.size()];

    for (int index = 0; index < usefulCoins.size(); index++) {
      result[index] = usefulCoins.get(index);
    }

    return result;
  }

  static void main() {
    System.out.println(findKthSmallest(new int[] {3, 6, 9}, 3)); // 9
    System.out.println(findKthSmallest(new int[] {5, 2}, 7)); // 12
  }
}
