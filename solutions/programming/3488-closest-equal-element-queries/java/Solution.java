package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> solveQueries(int[] nums, int[] queries) {
    int arrayLength = nums.length;

    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int index = 0; index < arrayLength; index++) {
      valueToIndices.computeIfAbsent(nums[index], ignored -> new ArrayList<>()).add(index);
    }

    List<Integer> answer = new ArrayList<>(queries.length);

    for (int queryIndex : queries) {
      List<Integer> sameValueIndices = valueToIndices.get(nums[queryIndex]);

      if (sameValueIndices.size() == 1) {
        answer.add(-1);
        continue;
      }

      int positionInIndices = findPosition(sameValueIndices, queryIndex);
      int indicesCount = sameValueIndices.size();

      int previousIndex =
          sameValueIndices.get((positionInIndices - 1 + indicesCount) % indicesCount);
      int nextIndex = sameValueIndices.get((positionInIndices + 1) % indicesCount);

      int distanceToPrevious = getCircularDistance(queryIndex, previousIndex, arrayLength);
      int distanceToNext = getCircularDistance(queryIndex, nextIndex, arrayLength);

      answer.add(Math.min(distanceToPrevious, distanceToNext));
    }

    return answer;
  }

  private int findPosition(List<Integer> indices, int targetIndex) {
    int leftPointer = 0;
    int rightPointer = indices.size() - 1;

    while (leftPointer <= rightPointer) {
      int middleIndex = leftPointer + (rightPointer - leftPointer) / 2;
      int currentIndex = indices.get(middleIndex);

      if (currentIndex == targetIndex) {
        return middleIndex;
      } else if (currentIndex < targetIndex) {
        leftPointer = middleIndex + 1;
      } else {
        rightPointer = middleIndex - 1;
      }
    }

    return -1;
  }

  private int getCircularDistance(int firstIndex, int secondIndex, int arrayLength) {
    int directDistance = Math.abs(firstIndex - secondIndex);
    return Math.min(directDistance, arrayLength - directDistance);
  }

  static void main() {
    System.out.println(
        new Solution().solveQueries(new int[] {1, 3, 1, 4, 1, 3, 2}, new int[] {0, 3, 5}));
  }
}
