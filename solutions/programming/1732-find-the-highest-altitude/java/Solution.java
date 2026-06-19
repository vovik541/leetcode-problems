package dev.vkh.solutions;

class Solution {
  public static int largestAltitude(int[] gain) {
    int currentAltitude = 0;
    int highestAltitude = 0;

    for (int altitudeGain : gain) {
      currentAltitude += altitudeGain;
      highestAltitude = Math.max(highestAltitude, currentAltitude);
    }

    return highestAltitude;
  }

  static void main() {
    int[] gain1 = {-5, 1, 5, 0, -7};
    System.out.println(largestAltitude(gain1)); // Output: 1

    int[] gain2 = {-4, -3, -2, -1, 4, 3, 2};
    System.out.println(largestAltitude(gain2)); // Output: 0
  }
}
