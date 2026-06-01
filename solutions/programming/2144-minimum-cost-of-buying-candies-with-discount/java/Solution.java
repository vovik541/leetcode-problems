package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  public int minimumCost(int[] cost) {
    Arrays.sort(cost);
    int totalCost = 0;
    int step = 1;

    for (int i = cost.length - 1; i >= 0; i--) {
      if (step == 3) {
        step = 1;
        continue;
      }

      step++;
      totalCost += cost[i];
    }

    return totalCost;
  }

  static void main() {
    System.out.println(new Solution().minimumCost(new int[] {6, 5, 7, 9, 2, 2}));
  }
}
