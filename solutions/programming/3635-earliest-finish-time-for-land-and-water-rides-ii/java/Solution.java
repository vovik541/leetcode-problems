package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int earliestFinishTime(
      int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
    RideData landRideData = buildRideData(landStartTime, landDuration);
    RideData waterRideData = buildRideData(waterStartTime, waterDuration);

    int bestLandThenWater = calculateBestFinishTime(landRideData, waterRideData);
    int bestWaterThenLand = calculateBestFinishTime(waterRideData, landRideData);

    return Math.min(bestLandThenWater, bestWaterThenLand);
  }

  private int calculateBestFinishTime(RideData firstRideData, RideData secondRideData) {
    int minimumFinishTime = Integer.MAX_VALUE;

    for (int[] firstRide : firstRideData.rides) {
      int firstRideFinishTime = firstRide[0] + firstRide[1];

      int lastAvailableSecondRideIndex =
          findLastRideStartingAtOrBefore(secondRideData.rides, firstRideFinishTime);

      if (lastAvailableSecondRideIndex >= 0) {
        minimumFinishTime =
            Math.min(
                minimumFinishTime,
                firstRideFinishTime
                    + secondRideData.prefixMinimumDuration[lastAvailableSecondRideIndex]);
      }

      int firstLaterSecondRideIndex = lastAvailableSecondRideIndex + 1;

      if (firstLaterSecondRideIndex < secondRideData.rides.length) {
        minimumFinishTime =
            Math.min(
                minimumFinishTime,
                secondRideData.suffixMinimumFinishTime[firstLaterSecondRideIndex]);
      }
    }

    return minimumFinishTime;
  }

  private RideData buildRideData(int[] startTimes, int[] durations) {
    int rideCount = startTimes.length;
    int[][] rides = new int[rideCount][2];

    for (int index = 0; index < rideCount; index++) {
      rides[index][0] = startTimes[index];
      rides[index][1] = durations[index];
    }

    Arrays.sort(rides, (firstRide, secondRide) -> firstRide[0] - secondRide[0]);

    int[] prefixMinimumDuration = new int[rideCount];
    prefixMinimumDuration[0] = rides[0][1];

    for (int index = 1; index < rideCount; index++) {
      prefixMinimumDuration[index] = Math.min(prefixMinimumDuration[index - 1], rides[index][1]);
    }

    int[] suffixMinimumFinishTime = new int[rideCount];
    suffixMinimumFinishTime[rideCount - 1] = rides[rideCount - 1][0] + rides[rideCount - 1][1];

    for (int index = rideCount - 2; index >= 0; index--) {
      suffixMinimumFinishTime[index] =
          Math.min(suffixMinimumFinishTime[index + 1], rides[index][0] + rides[index][1]);
    }

    return new RideData(rides, prefixMinimumDuration, suffixMinimumFinishTime);
  }

  private int findLastRideStartingAtOrBefore(int[][] rides, int targetTime) {
    int leftIndex = 0;
    int rightIndex = rides.length - 1;
    int answerIndex = -1;

    while (leftIndex <= rightIndex) {
      int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;

      if (rides[middleIndex][0] <= targetTime) {
        answerIndex = middleIndex;
        leftIndex = middleIndex + 1;
      } else {
        rightIndex = middleIndex - 1;
      }
    }

    return answerIndex;
  }

  private static class RideData {
    private final int[][] rides;
    private final int[] prefixMinimumDuration;
    private final int[] suffixMinimumFinishTime;

    private RideData(int[][] rides, int[] prefixMinimumDuration, int[] suffixMinimumFinishTime) {
      this.rides = rides;
      this.prefixMinimumDuration = prefixMinimumDuration;
      this.suffixMinimumFinishTime = suffixMinimumFinishTime;
    }
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
        solution.earliestFinishTime(
            new int[] {2, 8}, new int[] {4, 1}, new int[] {6}, new int[] {3})); // 9
  }
}
