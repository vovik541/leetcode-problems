package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  public static int maximumLength(int[] nums) {
    Map<Long, Integer> frequencyByNumber = new HashMap<>();

    for (int num : nums) {
      frequencyByNumber.put((long) num, frequencyByNumber.getOrDefault((long) num, 0) + 1);
    }

    int maxSubsetLength = 1;

    int onesCount = frequencyByNumber.getOrDefault(1L, 0);

    if (onesCount > 0) {
      if (onesCount % 2 == 1) {
        maxSubsetLength = Math.max(maxSubsetLength, onesCount);
      } else {
        maxSubsetLength = Math.max(maxSubsetLength, onesCount - 1);
      }
    }

    for (long startNumber : frequencyByNumber.keySet()) {
      if (startNumber == 1L) {
        continue;
      }

      long currentNumber = startNumber;
      int currentSubsetLength = 1;

      while (frequencyByNumber.getOrDefault(currentNumber, 0) >= 2) {
        long nextNumber = currentNumber * currentNumber;

        if (!frequencyByNumber.containsKey(nextNumber)) {
          break;
        }

        currentSubsetLength += 2;
        currentNumber = nextNumber;
      }

      maxSubsetLength = Math.max(maxSubsetLength, currentSubsetLength);
    }

    return maxSubsetLength;
  }

  static void main() {
    int[] nums1 = {5, 4, 1, 2, 2};
    System.out.println(maximumLength(nums1)); // Output: 3

    int[] nums2 = {1, 3, 2, 4};
    System.out.println(maximumLength(nums2)); // Output: 1
  }
}
