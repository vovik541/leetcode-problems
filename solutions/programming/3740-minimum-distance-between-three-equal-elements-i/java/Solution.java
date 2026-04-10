package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int minimumDistance(int[] nums) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();

    for (int index = 0; index < nums.length; index++) {
      valueToIndices.computeIfAbsent(nums[index], ignored -> new ArrayList<>()).add(index);
    }

    int minimumDistance = Integer.MAX_VALUE;

    for (List<Integer> indices : valueToIndices.values()) {
      if (indices.size() < 3) {
        continue;
      }

      for (int startIndex = 0; startIndex <= indices.size() - 3; startIndex++) {
        int leftIndex = indices.get(startIndex);
        int rightIndex = indices.get(startIndex + 2);

        minimumDistance = Math.min(minimumDistance, 2 * (rightIndex - leftIndex));
      }
    }

    return minimumDistance == Integer.MAX_VALUE ? -1 : minimumDistance;
  }

  static void main() {
    System.out.println(new Solution().minimumDistance(new int[] {1, 2, 1, 1, 3})); // 6
  }
}
