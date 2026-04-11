package dev.vkh.solutions;

class Solution {
  public int minimumDistance(int[] nums) {
    int arrayLength = nums.length;

    int[] firstIndex = new int[arrayLength + 1];
    int[] secondIndex = new int[arrayLength + 1];
    int[] occurrenceCount = new int[arrayLength + 1];

    java.util.Arrays.fill(firstIndex, -1);
    java.util.Arrays.fill(secondIndex, -1);

    int minimumDistance = Integer.MAX_VALUE;

    for (int currentIndex = 0; currentIndex < arrayLength; currentIndex++) {
      int currentValue = nums[currentIndex];

      if (occurrenceCount[currentValue] == 0) {
        firstIndex[currentValue] = currentIndex;
        occurrenceCount[currentValue] = 1;
      } else if (occurrenceCount[currentValue] == 1) {
        secondIndex[currentValue] = currentIndex;
        occurrenceCount[currentValue] = 2;
      } else {
        minimumDistance = Math.min(minimumDistance, 2 * (currentIndex - firstIndex[currentValue]));

        firstIndex[currentValue] = secondIndex[currentValue];
        secondIndex[currentValue] = currentIndex;
        occurrenceCount[currentValue] = 3;
      }
    }

    return minimumDistance == Integer.MAX_VALUE ? -1 : minimumDistance;
  }
}
