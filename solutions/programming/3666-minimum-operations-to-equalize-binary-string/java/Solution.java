package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeSet;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minOperations("110", 1)); // 1
    System.out.println(solution.minOperations("0101", 3)); // 2
    System.out.println(solution.minOperations("101", 2)); // -1
    System.out.println(solution.minOperations("1", 1)); // 0
  }

  public int minOperations(String s, int k) {
    int n = s.length();
    int initialZerosCount = 0;
    for (int index = 0; index < n; index++) {
      if (s.charAt(index) == '0') {
        initialZerosCount++;
      }
    }

    if (initialZerosCount == 0) {
      return 0;
    }

    int[] minOperationsToReachZerosCount = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      minOperationsToReachZerosCount[i] = -1;
    }

    TreeSet<Integer> unvisitedEvenZerosCounts = new TreeSet<>();
    TreeSet<Integer> unvisitedOddZerosCounts = new TreeSet<>();
    for (int zerosCount = 0; zerosCount <= n; zerosCount++) {
      if (zerosCount == initialZerosCount) {
        continue;
      }
      if ((zerosCount & 1) == 0) {
        unvisitedEvenZerosCounts.add(zerosCount);
      } else {
        unvisitedOddZerosCounts.add(zerosCount);
      }
    }

    Queue<Integer> bfsQueue = new ArrayDeque<>();
    bfsQueue.add(initialZerosCount);
    minOperationsToReachZerosCount[initialZerosCount] = 0;

    while (!bfsQueue.isEmpty()) {
      int currentZerosCount = bfsQueue.poll();
      int currentOperationsCount = minOperationsToReachZerosCount[currentZerosCount];

      int onesCount = n - currentZerosCount;

      int minZerosFlippedToOnes = Math.max(0, k - onesCount);
      int maxZerosFlippedToOnes = Math.min(k, currentZerosCount);

      if (minZerosFlippedToOnes > maxZerosFlippedToOnes) {
        continue;
      }

      int nextZerosCountLow = currentZerosCount + k - 2 * maxZerosFlippedToOnes;
      int nextZerosCountHigh = currentZerosCount + k - 2 * minZerosFlippedToOnes;

      int nextParity = (currentZerosCount + k) & 1;
      TreeSet<Integer> unvisitedWithRequiredParity =
          (nextParity == 0) ? unvisitedEvenZerosCounts : unvisitedOddZerosCounts;

      Integer candidateZerosCount = unvisitedWithRequiredParity.ceiling(nextZerosCountLow);
      while (candidateZerosCount != null && candidateZerosCount <= nextZerosCountHigh) {
        unvisitedWithRequiredParity.remove(candidateZerosCount);

        minOperationsToReachZerosCount[candidateZerosCount] = currentOperationsCount + 1;
        if (candidateZerosCount == 0) {
          return currentOperationsCount + 1;
        }

        bfsQueue.add(candidateZerosCount);
        candidateZerosCount = unvisitedWithRequiredParity.ceiling(nextZerosCountLow);
      }
    }

    return -1;
  }
}
