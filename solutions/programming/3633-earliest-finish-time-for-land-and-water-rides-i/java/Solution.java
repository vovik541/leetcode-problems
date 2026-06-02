package dev.vkh.solutions;

class Solution {
  public int earliestFinishTime(
      int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {

    int earliestFinish = Integer.MAX_VALUE;
    for (int landIndex = 0; landIndex < landStartTime.length; landIndex++) {
      int landFinishTime = landStartTime[landIndex] + landDuration[landIndex];
      for (int waterIndex = 0; waterIndex < waterStartTime.length; waterIndex++) {
        int waterRideStart = Math.max(landFinishTime, waterStartTime[waterIndex]);
        earliestFinish = Math.min(earliestFinish, waterRideStart + waterDuration[waterIndex]);
      }
    }

    for (int waterIndex = 0; waterIndex < waterStartTime.length; waterIndex++) {
      int waterFinishTime = waterStartTime[waterIndex] + waterDuration[waterIndex];
      for (int landIndex = 0; landIndex < landStartTime.length; landIndex++) {
        int landRideStart = Math.max(waterFinishTime, landStartTime[landIndex]);
        earliestFinish = Math.min(earliestFinish, landRideStart + landDuration[landIndex]);
      }
    }

    return earliestFinish;
  }

  static void main() {
    System.out.println(
        new Solution()
            .earliestFinishTime(
                new int[] {2, 8}, new int[] {4, 1}, new int[] {6}, new int[] {3})); // 9
  }
}
