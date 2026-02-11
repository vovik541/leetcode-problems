package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

  public int longestBalanced(int[] nums) {
    int n = nums.length;
    int maxValue = 100_000;

    @SuppressWarnings("unchecked")
    Deque<Integer>[] positionsByValue = new ArrayDeque[maxValue + 1];
    for (int i = 0; i <= maxValue; i++) {
      positionsByValue[i] = new ArrayDeque<>();
    }
    for (int index = 0; index < n; index++) {
      positionsByValue[nums[index]].addLast(index);
    }

    SegmentTreeLazy segmentTree = new SegmentTreeLazy(n);

    for (int value = 1; value <= maxValue; value++) {
      if (!positionsByValue[value].isEmpty()) {
        int firstPosition = positionsByValue[value].peekFirst();
        int sign = (value % 2 == 0) ? -1 : 1;
        segmentTree.rangeAdd(firstPosition, n - 1, sign);
      }
    }

    int bestLength = 0;

    for (int leftIndex = 0; leftIndex < n; leftIndex++) {
      int farthestRightWithZero = segmentTree.findRightmostZero(leftIndex, n - 1);
      if (farthestRightWithZero != -1) {
        bestLength = Math.max(bestLength, farthestRightWithZero - leftIndex + 1);
      }

      int value = nums[leftIndex];
      int sign = (value % 2 == 0) ? -1 : 1;

      Deque<Integer> positionsQueue = positionsByValue[value];

      int oldFirst = positionsQueue.pollFirst();

      segmentTree.rangeAdd(oldFirst, n - 1, -sign);

      if (!positionsQueue.isEmpty()) {
        int newFirst = positionsQueue.peekFirst();
        segmentTree.rangeAdd(newFirst, n - 1, sign);
      }
    }

    return bestLength;
  }

  private static final class SegmentTreeLazy {
    private final int size;
    private final int[] minValue;
    private final int[] maxValue;
    private final int[] lazyAdd;

    SegmentTreeLazy(int n) {
      this.size = n;
      int treeSize = 4 * n;
      this.minValue = new int[treeSize];
      this.maxValue = new int[treeSize];
      this.lazyAdd = new int[treeSize];
      build(1, 0, n - 1);
    }

    private void build(int node, int left, int right) {
      minValue[node] = 0;
      maxValue[node] = 0;
      lazyAdd[node] = 0;
      if (left == right) return;
      int mid = left + (right - left) / 2;
      build(node * 2, left, mid);
      build(node * 2 + 1, mid + 1, right);
    }

    public void rangeAdd(int queryLeft, int queryRight, int delta) {
      if (queryLeft > queryRight) return;
      rangeAdd(1, 0, size - 1, queryLeft, queryRight, delta);
    }

    private void rangeAdd(int node, int left, int right, int queryLeft, int queryRight, int delta) {
      if (queryRight < left || right < queryLeft) return;

      if (queryLeft <= left && right <= queryRight) {
        apply(node, delta);
        return;
      }

      push(node);

      int mid = left + (right - left) / 2;
      rangeAdd(node * 2, left, mid, queryLeft, queryRight, delta);
      rangeAdd(node * 2 + 1, mid + 1, right, queryLeft, queryRight, delta);

      pull(node);
    }

    private void apply(int node, int delta) {
      minValue[node] += delta;
      maxValue[node] += delta;
      lazyAdd[node] += delta;
    }

    private void push(int node) {
      int pending = lazyAdd[node];
      if (pending != 0) {
        apply(node * 2, pending);
        apply(node * 2 + 1, pending);
        lazyAdd[node] = 0;
      }
    }

    private void pull(int node) {
      minValue[node] = Math.min(minValue[node * 2], minValue[node * 2 + 1]);
      maxValue[node] = Math.max(maxValue[node * 2], maxValue[node * 2 + 1]);
    }

    public int findRightmostZero(int queryLeft, int queryRight) {
      if (queryLeft > queryRight) return -1;
      return findRightmostZero(1, 0, size - 1, queryLeft, queryRight);
    }

    private int findRightmostZero(int node, int left, int right, int queryLeft, int queryRight) {
      if (queryRight < left || right < queryLeft) return -1;

      if (minValue[node] > 0 || maxValue[node] < 0) return -1;

      if (left == right) {
        return left;
      }

      push(node);

      int mid = left + (right - left) / 2;

      int rightResult = findRightmostZero(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
      if (rightResult != -1) return rightResult;

      return findRightmostZero(node * 2, left, mid, queryLeft, queryRight);
    }
  }
}
