package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {
  public int minimumPairRemoval(int[] nums) {
    List<Long> values = new ArrayList<>(nums.length);
    for (int number : nums) {
      values.add((long) number);
    }

    int operationCount = 0;

    while (!isNonDecreasing(values)) {
      int bestPairStartIndex = 0;
      long smallestPairSum = values.get(0) + values.get(1);

      for (int index = 1; index < values.size() - 1; index++) {
        long currentPairSum = values.get(index) + values.get(index + 1);
        if (currentPairSum < smallestPairSum) {
          smallestPairSum = currentPairSum;
          bestPairStartIndex = index;
        }
      }

      values.set(bestPairStartIndex, smallestPairSum);
      values.remove(bestPairStartIndex + 1);

      operationCount++;
    }

    return operationCount;
  }

  private boolean isNonDecreasing(List<Long> values) {
    for (int index = 1; index < values.size(); index++) {
      if (values.get(index) < values.get(index - 1)) {
        return false;
      }
    }
    return true;
  }

  static void main() {
    System.out.println(new Solution().minimumPairRemoval(new int[] {5, 2, 3, 1}));
  }
}
