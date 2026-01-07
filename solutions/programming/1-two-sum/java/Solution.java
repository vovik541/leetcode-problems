package dev.vkh.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
  static void main(String[] args) {
    Solution solution = new Solution();

    int[] nums1 = {2, 7, 11, 15};
    int target1 = 9;
    System.out.println(Arrays.toString(solution.twoSum(nums1, target1)));

    int[] nums2 = {3, 2, 4};
    int target2 = 6;
    System.out.println(Arrays.toString(solution.twoSum(nums2, target2)));
  }

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> valueToIndexMap = new HashMap<>();

    for (int currentIndex = 0; currentIndex < nums.length; currentIndex++) {
      int currentValue = nums[currentIndex];
      int requiredValue = target - currentValue;

      if (valueToIndexMap.containsKey(requiredValue)) {
        return new int[] {valueToIndexMap.get(requiredValue), currentIndex};
      }

      valueToIndexMap.put(currentValue, currentIndex);
    }

    throw new IllegalArgumentException("No valid solution exists");
  }
}
