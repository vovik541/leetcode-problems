package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

class Solution {

  public int minJumps(int[] nums) {
    int arrayLength = nums.length;

    if (arrayLength == 1) {
      return 0;
    }

    int maximumValue = 0;
    for (int value : nums) {
      maximumValue = Math.max(maximumValue, value);
    }

    int[] smallestPrimeFactor = buildSmallestPrimeFactor(maximumValue);

    List<Integer>[] indicesByPrimeFactor = new ArrayList[maximumValue + 1];

    for (int index = 0; index < arrayLength; index++) {
      for (int primeFactor : getUniquePrimeFactors(nums[index], smallestPrimeFactor)) {
        if (indicesByPrimeFactor[primeFactor] == null) {
          indicesByPrimeFactor[primeFactor] = new ArrayList<>();
        }
        indicesByPrimeFactor[primeFactor].add(index);
      }
    }

    int[] jumps = new int[arrayLength];
    Arrays.fill(jumps, -1);

    Queue<Integer> indicesToVisit = new ArrayDeque<>();
    indicesToVisit.offer(0);
    jumps[0] = 0;

    boolean[] usedPrimeTeleport = new boolean[maximumValue + 1];

    while (!indicesToVisit.isEmpty()) {
      int currentIndex = indicesToVisit.poll();

      if (currentIndex == arrayLength - 1) {
        return jumps[currentIndex];
      }

      addIndexIfUnvisited(currentIndex - 1, jumps, indicesToVisit, jumps[currentIndex] + 1);
      addIndexIfUnvisited(currentIndex + 1, jumps, indicesToVisit, jumps[currentIndex] + 1);

      int currentValue = nums[currentIndex];

      if (isPrime(currentValue, smallestPrimeFactor) && !usedPrimeTeleport[currentValue]) {
        usedPrimeTeleport[currentValue] = true;

        List<Integer> teleportTargetIndices = indicesByPrimeFactor[currentValue];

        if (teleportTargetIndices != null) {
          for (int targetIndex : teleportTargetIndices) {
            addIndexIfUnvisited(targetIndex, jumps, indicesToVisit, jumps[currentIndex] + 1);
          }
        }
      }
    }

    return -1;
  }

  private void addIndexIfUnvisited(
      int index, int[] jumps, Queue<Integer> indicesToVisit, int nextJumpCount) {
    if (index < 0 || index >= jumps.length || jumps[index] != -1) {
      return;
    }

    jumps[index] = nextJumpCount;
    indicesToVisit.offer(index);
  }

  private int[] buildSmallestPrimeFactor(int maximumValue) {
    int[] smallestPrimeFactor = new int[maximumValue + 1];

    for (int value = 2; value <= maximumValue; value++) {
      if (smallestPrimeFactor[value] == 0) {
        smallestPrimeFactor[value] = value;

        if ((long) value * value <= maximumValue) {
          for (long multiple = (long) value * value; multiple <= maximumValue; multiple += value) {
            if (smallestPrimeFactor[(int) multiple] == 0) {
              smallestPrimeFactor[(int) multiple] = value;
            }
          }
        }
      }
    }

    return smallestPrimeFactor;
  }

  private boolean isPrime(int value, int[] smallestPrimeFactor) {
    return value >= 2 && smallestPrimeFactor[value] == value;
  }

  private List<Integer> getUniquePrimeFactors(int value, int[] smallestPrimeFactor) {
    List<Integer> primeFactors = new ArrayList<>();

    while (value > 1) {
      int primeFactor = smallestPrimeFactor[value];
      primeFactors.add(primeFactor);

      while (value % primeFactor == 0) {
        value /= primeFactor;
      }
    }

    return primeFactors;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minJumps(new int[] {1, 2, 4, 6})); // 2
    System.out.println(solution.minJumps(new int[] {2, 3, 4, 7, 9})); // 2
    System.out.println(solution.minJumps(new int[] {4, 6, 5, 8})); // 3
  }
}
