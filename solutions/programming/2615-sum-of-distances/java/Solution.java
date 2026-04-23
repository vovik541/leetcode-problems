package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long[] distance(int[] nums) {
    Map<Integer, List<Integer>> indicesByValue = new HashMap<>();

    for (int index = 0; index < nums.length; index++) {
      indicesByValue.computeIfAbsent(nums[index], ignoredValue -> new ArrayList<>()).add(index);
    }

    long[] distances = new long[nums.length];

    for (List<Integer> indicesWithSameValue : indicesByValue.values()) {
      int groupSize = indicesWithSameValue.size();

      if (groupSize == 1) {
        continue;
      }

      long[] prefixSums = new long[groupSize];
      prefixSums[0] = indicesWithSameValue.get(0);

      for (int index = 1; index < groupSize; index++) {
        prefixSums[index] = prefixSums[index - 1] + indicesWithSameValue.get(index);
      }

      for (int index = 0; index < groupSize; index++) {
        long currentIndex = indicesWithSameValue.get(index);

        long leftDistanceSum = currentIndex * index - (index > 0 ? prefixSums[index - 1] : 0L);

        long rightDistanceSum =
            (prefixSums[groupSize - 1] - prefixSums[index])
                - currentIndex * (groupSize - 1 - index);

        distances[(int) currentIndex] = leftDistanceSum + rightDistanceSum;
      }
    }

    return distances;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
        Arrays.toString(solution.distance(new int[] {1, 3, 1, 1, 2})));
    System.out.println(Arrays.toString(solution.distance(new int[] {0, 5, 3})));
  }
}
