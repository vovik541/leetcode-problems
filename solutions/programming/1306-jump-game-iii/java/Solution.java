package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

  public boolean canReach(int[] arr, int start) {
    int arrayLength = arr.length;

    boolean[] visited = new boolean[arrayLength];
    Queue<Integer> indicesToVisit = new ArrayDeque<>();

    indicesToVisit.offer(start);
    visited[start] = true;

    while (!indicesToVisit.isEmpty()) {
      int currentIndex = indicesToVisit.poll();

      if (arr[currentIndex] == 0) {
        return true;
      }

      int forwardJumpIndex = currentIndex + arr[currentIndex];
      int backwardJumpIndex = currentIndex - arr[currentIndex];

      if (isValidIndex(forwardJumpIndex, arrayLength) && !visited[forwardJumpIndex]) {

        visited[forwardJumpIndex] = true;
        indicesToVisit.offer(forwardJumpIndex);
      }

      if (isValidIndex(backwardJumpIndex, arrayLength) && !visited[backwardJumpIndex]) {

        visited[backwardJumpIndex] = true;
        indicesToVisit.offer(backwardJumpIndex);
      }
    }

    return false;
  }

  private boolean isValidIndex(int index, int arrayLength) {
    return index >= 0 && index < arrayLength;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.canReach(new int[] {4, 2, 3, 0, 3, 1, 2}, 0)); // true

    System.out.println(solution.canReach(new int[] {3, 0, 2, 1, 2}, 2)); // false
  }
}
