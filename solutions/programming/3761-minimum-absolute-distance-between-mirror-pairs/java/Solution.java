package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minMirrorPairDistance(int[] nums) {
    Map<Integer, Integer> reversedValueToLatestIndex = new HashMap<>();
    int minimumDistance = Integer.MAX_VALUE;

    for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
      int currentValue = nums[currentIndex];

      if (reversedValueToLatestIndex.containsKey(currentValue)) {
        minimumDistance =
            Math.min(minimumDistance, currentIndex - reversedValueToLatestIndex.get(currentValue));
      }

      int reversedCurrentValue = reverseNumber(currentValue);
      reversedValueToLatestIndex.put(reversedCurrentValue, currentIndex);
    }

    return minimumDistance == Integer.MAX_VALUE ? -1 : minimumDistance;
  }

  private int reverseNumber(int number) {
    int reversedNumber = 0;

    while (number > 0) {
      reversedNumber = reversedNumber * 10 + (number % 10);
      number /= 10;
    }

    return reversedNumber;
  }

  static void main() {
    System.out.println(new Solution().minMirrorPairDistance(new int[] {12, 21, 45, 33, 5}));
  }
}
