package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public boolean asteroidsDestroyed(int mass, int[] asteroids) {
    Arrays.sort(asteroids);

    long currentMass = mass;

    for (int asteroidMass : asteroids) {
      if (currentMass < asteroidMass) {
        return false;
      }

      currentMass += asteroidMass;
    }

    return true;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.asteroidsDestroyed(10, new int[] {3, 9, 19, 5, 21})); // true
  }
}
