package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {

  public int minJumps(int[] arr) {
    int arrayLength = arr.length;

    if (arrayLength == 1) {
      return 0;
    }

    Map<Integer, List<Integer>> indicesByValue = new HashMap<>();

    for (int index = 0; index < arrayLength; index++) {
      indicesByValue.computeIfAbsent(arr[index], ignoredValue -> new ArrayList<>()).add(index);
    }

    boolean[] visited = new boolean[arrayLength];
    Queue<Integer> indicesToVisit = new ArrayDeque<>();

    indicesToVisit.offer(0);
    visited[0] = true;

    int jumpsCount = 0;

    while (!indicesToVisit.isEmpty()) {
      int levelSize = indicesToVisit.size();

      for (int levelIndex = 0; levelIndex < levelSize; levelIndex++) {
        int currentIndex = indicesToVisit.poll();

        if (currentIndex == arrayLength - 1) {
          return jumpsCount;
        }

        addIndexIfValid(currentIndex - 1, arr, visited, indicesToVisit);
        addIndexIfValid(currentIndex + 1, arr, visited, indicesToVisit);

        List<Integer> sameValueIndices = indicesByValue.get(arr[currentIndex]);

        if (sameValueIndices != null) {
          for (int nextIndex : sameValueIndices) {
            addIndexIfValid(nextIndex, arr, visited, indicesToVisit);
          }

          indicesByValue.remove(arr[currentIndex]);
        }
      }

      jumpsCount++;
    }

    return -1;
  }

  private void addIndexIfValid(
      int index, int[] arr, boolean[] visited, Queue<Integer> indicesToVisit) {
    if (index < 0 || index >= arr.length || visited[index]) {
      return;
    }

    visited[index] = true;
    indicesToVisit.offer(index);
  }

  static void main() {
    System.out.println(
        new Solution().minJumps(new int[] {100, -23, -23, 404, 100, 23, 23, 23, 3, 404})); // 3
  }
}
