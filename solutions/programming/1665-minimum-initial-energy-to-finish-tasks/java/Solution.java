package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int minimumEffort(int[][] tasks) {
    Arrays.sort(
        tasks,
        (firstTask, secondTask) -> (secondTask[1] - secondTask[0]) - (firstTask[1] - firstTask[0]));

    int minimumInitialEnergy = 0;
    int currentEnergy = 0;

    for (int[] task : tasks) {
      int actualEnergyCost = task[0];
      int minimumRequiredEnergy = task[1];

      if (currentEnergy < minimumRequiredEnergy) {
        int additionalEnergyNeeded = minimumRequiredEnergy - currentEnergy;
        minimumInitialEnergy += additionalEnergyNeeded;
        currentEnergy += additionalEnergyNeeded;
      }

      currentEnergy -= actualEnergyCost;
    }

    return minimumInitialEnergy;
  }

  static void main() {

    int[][] tasks = {
      {1, 3},
      {2, 4},
      {10, 11},
      {10, 12},
      {8, 9}
    };
    System.out.println(new Solution().minimumEffort(tasks)); // 32
  }
}
