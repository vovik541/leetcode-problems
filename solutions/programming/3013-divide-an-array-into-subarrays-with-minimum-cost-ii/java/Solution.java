package dev.vkh.solutions;

import java.util.*;

public class Solution {

  private static final class ValueIndexPair {
    final long value;
    final int index;

    ValueIndexPair(long value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  public long minimumCost(int[] nums, int k, int dist) {
    int arrayLength = nums.length;
    int numberOfAdditionalStartsNeeded = k - 2; // besides nums[0] and nums[i]

    int lastPossibleSecondStartIndex = arrayLength - k + 1;

    PriorityQueue<ValueIndexPair> selectedSmallestMaxHeap =
        new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));

    PriorityQueue<ValueIndexPair> remainingMinHeap =
        new PriorityQueue<>(Comparator.comparingLong(a -> a.value));

    Map<Integer, Boolean> indexToIsSelected = new HashMap<>();

    Set<Integer> removedIndices = new HashSet<>();

    long selectedSum = 0L;
    long bestAnswer = Long.MAX_VALUE;

    int currentSecondStartIndex = 1;
    int windowLeftIndex = currentSecondStartIndex + 1;
    int windowRightIndex = Math.min(currentSecondStartIndex + dist, arrayLength - 1);

    for (int index = windowLeftIndex; index <= windowRightIndex; index++) {
      remainingMinHeap.offer(new ValueIndexPair(nums[index], index));
      indexToIsSelected.put(index, false);
    }

    selectedSum +=
        moveFromRemainingToSelectedUntilFull(
            remainingMinHeap,
            selectedSmallestMaxHeap,
            indexToIsSelected,
            removedIndices,
            windowLeftIndex,
            windowRightIndex,
            numberOfAdditionalStartsNeeded);

    selectedSum =
        rebalanceHeaps(
            selectedSmallestMaxHeap,
            remainingMinHeap,
            indexToIsSelected,
            removedIndices,
            windowLeftIndex,
            windowRightIndex,
            numberOfAdditionalStartsNeeded,
            selectedSum);

    for (currentSecondStartIndex = 1;
        currentSecondStartIndex <= lastPossibleSecondStartIndex;
        currentSecondStartIndex++) {

      selectedSum =
          ensureSelectedSizeAndRebalance(
              selectedSmallestMaxHeap,
              remainingMinHeap,
              indexToIsSelected,
              removedIndices,
              windowLeftIndex,
              windowRightIndex,
              numberOfAdditionalStartsNeeded,
              selectedSum);

      long currentCost = (long) nums[0] + (long) nums[currentSecondStartIndex] + selectedSum;
      bestAnswer = Math.min(bestAnswer, currentCost);

      if (currentSecondStartIndex == lastPossibleSecondStartIndex) {
        break;
      }

      int outgoingIndex = currentSecondStartIndex + 1;
      int incomingIndex = currentSecondStartIndex + dist + 1;
      Boolean wasSelected = indexToIsSelected.get(outgoingIndex);
      if (wasSelected != null) {
        if (wasSelected) {
          selectedSum -= nums[outgoingIndex];
        }
        indexToIsSelected.remove(outgoingIndex);
        removedIndices.add(outgoingIndex);
      }

      windowLeftIndex++;
      windowRightIndex = Math.min(windowRightIndex + 1, arrayLength - 1);

      if (incomingIndex <= arrayLength - 1) {
        remainingMinHeap.offer(new ValueIndexPair(nums[incomingIndex], incomingIndex));
        indexToIsSelected.put(incomingIndex, false);
      }
    }

    return bestAnswer;
  }

  private static long ensureSelectedSizeAndRebalance(
      PriorityQueue<ValueIndexPair> selectedSmallestMaxHeap,
      PriorityQueue<ValueIndexPair> remainingMinHeap,
      Map<Integer, Boolean> indexToIsSelected,
      Set<Integer> removedIndices,
      int windowLeftIndex,
      int windowRightIndex,
      int targetSelectedSize,
      long selectedSum) {
    pruneSelectedTop(
        selectedSmallestMaxHeap,
        indexToIsSelected,
        removedIndices,
        windowLeftIndex,
        windowRightIndex);
    pruneRemainingTop(
        remainingMinHeap, indexToIsSelected, removedIndices, windowLeftIndex, windowRightIndex);

    while (getActiveSelectedSize(indexToIsSelected) < targetSelectedSize) {
      pruneRemainingTop(
          remainingMinHeap, indexToIsSelected, removedIndices, windowLeftIndex, windowRightIndex);
      ValueIndexPair smallestRemaining = remainingMinHeap.poll();
      if (smallestRemaining == null) break;

      indexToIsSelected.put(smallestRemaining.index, true);
      selectedSmallestMaxHeap.offer(smallestRemaining);
      selectedSum += smallestRemaining.value;
    }

    return rebalanceHeaps(
        selectedSmallestMaxHeap,
        remainingMinHeap,
        indexToIsSelected,
        removedIndices,
        windowLeftIndex,
        windowRightIndex,
        targetSelectedSize,
        selectedSum);
  }

  private static int getActiveSelectedSize(Map<Integer, Boolean> indexToIsSelected) {
    int activeSelectedCount = 0;
    for (boolean isSelected : indexToIsSelected.values()) {
      if (isSelected) activeSelectedCount++;
    }
    return activeSelectedCount;
  }

  private static long moveFromRemainingToSelectedUntilFull(
      PriorityQueue<ValueIndexPair> remainingMinHeap,
      PriorityQueue<ValueIndexPair> selectedSmallestMaxHeap,
      Map<Integer, Boolean> indexToIsSelected,
      Set<Integer> removedIndices,
      int windowLeftIndex,
      int windowRightIndex,
      int targetSelectedSize) {
    long addedToSelectedSum = 0L;

    while (getActiveSelectedSize(indexToIsSelected) < targetSelectedSize) {
      pruneRemainingTop(
          remainingMinHeap, indexToIsSelected, removedIndices, windowLeftIndex, windowRightIndex);
      ValueIndexPair smallestRemaining = remainingMinHeap.poll();
      if (smallestRemaining == null) break;

      indexToIsSelected.put(smallestRemaining.index, true);
      selectedSmallestMaxHeap.offer(smallestRemaining);
      addedToSelectedSum += smallestRemaining.value;
    }

    return addedToSelectedSum;
  }

  private static long rebalanceHeaps(
      PriorityQueue<ValueIndexPair> selectedSmallestMaxHeap,
      PriorityQueue<ValueIndexPair> remainingMinHeap,
      Map<Integer, Boolean> indexToIsSelected,
      Set<Integer> removedIndices,
      int windowLeftIndex,
      int windowRightIndex,
      int targetSelectedSize,
      long selectedSum) {
    pruneSelectedTop(
        selectedSmallestMaxHeap,
        indexToIsSelected,
        removedIndices,
        windowLeftIndex,
        windowRightIndex);
    pruneRemainingTop(
        remainingMinHeap, indexToIsSelected, removedIndices, windowLeftIndex, windowRightIndex);

    while (getActiveSelectedSize(indexToIsSelected) > targetSelectedSize) {
      pruneSelectedTop(
          selectedSmallestMaxHeap,
          indexToIsSelected,
          removedIndices,
          windowLeftIndex,
          windowRightIndex);
      ValueIndexPair largestSelected = selectedSmallestMaxHeap.poll();
      if (largestSelected == null) break;

      indexToIsSelected.put(largestSelected.index, false);
      remainingMinHeap.offer(largestSelected);
      selectedSum -= largestSelected.value;
    }

    while (true) {
      pruneSelectedTop(
          selectedSmallestMaxHeap,
          indexToIsSelected,
          removedIndices,
          windowLeftIndex,
          windowRightIndex);
      pruneRemainingTop(
          remainingMinHeap, indexToIsSelected, removedIndices, windowLeftIndex, windowRightIndex);

      ValueIndexPair largestSelected = selectedSmallestMaxHeap.peek();
      ValueIndexPair smallestRemaining = remainingMinHeap.peek();

      if (largestSelected == null || smallestRemaining == null) break;
      if (largestSelected.value <= smallestRemaining.value) break;

      selectedSmallestMaxHeap.poll();
      remainingMinHeap.poll();

      indexToIsSelected.put(largestSelected.index, false);
      indexToIsSelected.put(smallestRemaining.index, true);

      selectedSum -= largestSelected.value;
      selectedSum += smallestRemaining.value;

      selectedSmallestMaxHeap.offer(smallestRemaining);
      remainingMinHeap.offer(largestSelected);
    }

    return selectedSum;
  }

  private static void pruneSelectedTop(
      PriorityQueue<ValueIndexPair> selectedSmallestMaxHeap,
      Map<Integer, Boolean> indexToIsSelected,
      Set<Integer> removedIndices,
      int windowLeftIndex,
      int windowRightIndex) {
    while (!selectedSmallestMaxHeap.isEmpty()) {
      ValueIndexPair top = selectedSmallestMaxHeap.peek();

      boolean outOfWindow = top.index < windowLeftIndex || top.index > windowRightIndex;
      boolean removed = removedIndices.contains(top.index);
      Boolean stillSelected = indexToIsSelected.get(top.index);

      if (outOfWindow || removed || stillSelected == null || !stillSelected) {
        selectedSmallestMaxHeap.poll();
      } else {
        break;
      }
    }
  }

  private static void pruneRemainingTop(
      PriorityQueue<ValueIndexPair> remainingMinHeap,
      Map<Integer, Boolean> indexToIsSelected,
      Set<Integer> removedIndices,
      int windowLeftIndex,
      int windowRightIndex) {
    while (!remainingMinHeap.isEmpty()) {
      ValueIndexPair top = remainingMinHeap.peek();

      boolean outOfWindow = top.index < windowLeftIndex || top.index > windowRightIndex;
      boolean removed = removedIndices.contains(top.index);
      Boolean stillSelected = indexToIsSelected.get(top.index);

      if (outOfWindow || removed || stillSelected == null || stillSelected) {
        remainingMinHeap.poll();
      } else {
        break;
      }
    }
  }

  static void main() {
    Solution solution = new Solution();

    //    System.out.println(solution.minimumCost(new int[]{10, 1, 2, 2, 2, 1}, 4, 3));
    System.out.println(solution.minimumCost(new int[] {10, 8, 18, 9}, 3, 1));
  }
}
