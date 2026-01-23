package dev.vkh.solutions;

import java.util.PriorityQueue;

class Solution {
  private static final class PairEntry {
    final long sum;
    final int leftIndex;
    final int version;

    PairEntry(long sum, int leftIndex, int version) {
      this.sum = sum;
      this.leftIndex = leftIndex;
      this.version = version;
    }
  }

  public int minimumPairRemoval(int[] nums) {
    int n = nums.length;
    if (n <= 1) return 0;

    long[] values = new long[n];
    for (int i = 0; i < n; i++) values[i] = nums[i];

    int[] previousIndex = new int[n];
    int[] nextIndex = new int[n];
    boolean[] isAlive = new boolean[n];

    for (int i = 0; i < n; i++) {
      previousIndex[i] = i - 1;
      nextIndex[i] = (i + 1 < n) ? (i + 1) : -1;
      isAlive[i] = true;
    }

    // badCount = number of adjacent inversions (values[i] > values[next[i]])
    int badCount = 0;
    for (int i = 0; i < n; i++) {
      int j = nextIndex[i];
      if (j != -1 && values[i] > values[j]) badCount++;
    }
    if (badCount == 0) return 0;

    // For lazy deletion in PQ when pair sum changes
    int[] pairVersion = new int[n];

    PriorityQueue<PairEntry> minHeap =
        new PriorityQueue<>(
            (a, b) -> {
              if (a.sum != b.sum) return Long.compare(a.sum, b.sum);
              return Integer.compare(a.leftIndex, b.leftIndex); // leftmost tie-break
            });

    // Push all initial adjacent pairs
    for (int i = 0; i < n; i++) {
      if (nextIndex[i] != -1) {
        pairVersion[i]++;
        minHeap.add(new PairEntry(values[i] + values[nextIndex[i]], i, pairVersion[i]));
      }
    }

    int operationCount = 0;

    while (badCount > 0) {
      PairEntry best = minHeap.poll();
      // n>=2 so heap shouldn't be empty, but safe-guard:
      if (best == null) break;

      int left = best.leftIndex;

      // Validate pair is still current:
      if (!isAlive[left]) continue;
      int right = nextIndex[left];
      if (right == -1) continue;
      if (best.version != pairVersion[left]) continue;

      // Recompute sum to be extra safe:
      long currentSum = values[left] + values[right];
      if (currentSum != best.sum) continue;

      int leftNeighbor = previousIndex[left];
      int rightNeighbor = nextIndex[right];

      // Remove old "bad edges" contributions around (left,right)
      if (leftNeighbor != -1 && isAlive[leftNeighbor]) {
        if (values[leftNeighbor] > values[left]) badCount--;
      }
      if (values[left] > values[right]) badCount--;
      if (rightNeighbor != -1 && isAlive[rightNeighbor]) {
        if (values[right] > values[rightNeighbor]) badCount--;
      }

      // Merge (left,right) into left
      values[left] = currentSum;
      // Remove right from linked list
      isAlive[right] = false;
      nextIndex[left] = rightNeighbor;
      if (rightNeighbor != -1) previousIndex[rightNeighbor] = left;

      // Add new "bad edges" contributions after merge
      if (leftNeighbor != -1 && isAlive[leftNeighbor]) {
        if (values[leftNeighbor] > values[left]) badCount++;
      }
      if (rightNeighbor != -1 && isAlive[rightNeighbor]) {
        if (values[left] > values[rightNeighbor]) badCount++;
      }

      // Update affected pairs in heap: (leftNeighbor,left) and (left,rightNeighbor)
      pushPairIfExists(minHeap, values, nextIndex, isAlive, pairVersion, leftNeighbor);
      pushPairIfExists(minHeap, values, nextIndex, isAlive, pairVersion, left);

      operationCount++;
    }

    return operationCount;
  }

  private void pushPairIfExists(
      PriorityQueue<PairEntry> minHeap,
      long[] values,
      int[] nextIndex,
      boolean[] isAlive,
      int[] pairVersion,
      int leftIndex) {
    if (leftIndex == -1) return;
    if (!isAlive[leftIndex]) return;

    int rightIndex = nextIndex[leftIndex];
    if (rightIndex == -1) return; // no adjacent pair

    pairVersion[leftIndex]++;
    minHeap.add(
        new PairEntry(values[leftIndex] + values[rightIndex], leftIndex, pairVersion[leftIndex]));
  }

  static void main() {
    Solution solution = new Solution();

    int[] nums = {5, 2, 3, 1}; // 2
    //    int[] nums = {1, 2, 2};          // 0
    //    int[] nums = {4, 3, 2, 1};       // 3
    //    int[] nums = {10, -5, -6, 20};   // depends on merges

    System.out.println(solution.minimumPairRemoval(nums));
  }
}
