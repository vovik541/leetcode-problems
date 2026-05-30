package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

class Solution {

  public List<Boolean> getResults(int[][] queries) {
    int maxCoordinate = 0;
    for (int[] query : queries) {
      maxCoordinate = Math.max(maxCoordinate, query[1]);
    }

    SegmentTree segmentTree = new SegmentTree(maxCoordinate + 2);
    TreeSet<Integer> obstaclePositions = new TreeSet<>();
    obstaclePositions.add(0);

    List<Boolean> results = new ArrayList<>();

    for (int[] query : queries) {
      if (query[0] == 1) {
        int obstaclePosition = query[1];

        Integer previousObstacle = obstaclePositions.floor(obstaclePosition);
        Integer nextObstacle = obstaclePositions.ceiling(obstaclePosition);

        obstaclePositions.add(obstaclePosition);

        int leftGapLength = obstaclePosition - previousObstacle;
        segmentTree.update(obstaclePosition, leftGapLength);

        if (nextObstacle != null) {
          int updatedRightGapLength = nextObstacle - obstaclePosition;
          segmentTree.update(nextObstacle, updatedRightGapLength);
        }
      } else {
        int queryLimit = query[1];
        int blockSize = query[2];

        Integer previousObstacle = obstaclePositions.floor(queryLimit);

        int bestGapEndingAtObstacle = segmentTree.queryMax(0, queryLimit);
        int tailGapLength = queryLimit - previousObstacle;

        results.add(Math.max(bestGapEndingAtObstacle, tailGapLength) >= blockSize);
      }
    }

    return results;
  }

  private static class SegmentTree {
    private final int size;
    private final int[] maxValues;

    private SegmentTree(int size) {
      this.size = size;
      this.maxValues = new int[size * 4];
    }

    private void update(int index, int value) {
      update(1, 0, size - 1, index, value);
    }

    private void update(int node, int left, int right, int index, int value) {
      if (left == right) {
        maxValues[node] = value;
        return;
      }

      int middle = left + (right - left) / 2;

      if (index <= middle) {
        update(node * 2, left, middle, index, value);
      } else {
        update(node * 2 + 1, middle + 1, right, index, value);
      }

      maxValues[node] = Math.max(maxValues[node * 2], maxValues[node * 2 + 1]);
    }

    private int queryMax(int queryLeft, int queryRight) {
      return queryMax(1, 0, size - 1, queryLeft, queryRight);
    }

    private int queryMax(int node, int left, int right, int queryLeft, int queryRight) {
      if (queryRight < left || right < queryLeft) {
        return 0;
      }

      if (queryLeft <= left && right <= queryRight) {
        return maxValues[node];
      }

      int middle = left + (right - left) / 2;

      return Math.max(
          queryMax(node * 2, left, middle, queryLeft, queryRight),
          queryMax(node * 2 + 1, middle + 1, right, queryLeft, queryRight));
    }
  }

  static void main() {
    Solution solution = new Solution();
    int[][] queries2 = {
      {1, 7},
      {2, 7, 6},
      {1, 2},
      {2, 7, 5},
      {2, 7, 6}
    };
    System.out.println(solution.getResults(queries2)); // [true, true, false]
  }
}
