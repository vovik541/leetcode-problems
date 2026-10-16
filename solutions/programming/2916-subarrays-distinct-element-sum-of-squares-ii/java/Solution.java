package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  private static final long MOD = 1_000_000_007L;

  public static int sumCounts(int[] nums) {
    int length = nums.length;

    SegmentTree tree = new SegmentTree(length);

    int[] lastOccurrence = new int[100_001];
    Arrays.fill(lastOccurrence, -1);

    long result = 0;

    for (int right = 0; right < length; right++) {
      int previousIndex = lastOccurrence[nums[right]];

      tree.add(previousIndex + 1, right, 1);

      result = (result + tree.getSquareSum()) % MOD;

      lastOccurrence[nums[right]] = right;
    }

    return (int) result;
  }

  private static class SegmentTree {

    private final long[] sums;
    private final long[] squareSums;
    private final long[] lazy;
    private final int size;

    SegmentTree(int size) {
      this.size = size;
      this.sums = new long[size * 4];
      this.squareSums = new long[size * 4];
      this.lazy = new long[size * 4];
    }

    void add(int left, int right, long value) {
      update(1, 0, size - 1, left, right, value);
    }

    long getSquareSum() {
      return squareSums[1];
    }

    private void update(int node, int start, int end, int left, int right, long value) {

      if (right < start || end < left) {
        return;
      }

      if (left <= start && end <= right) {
        apply(node, start, end, value);
        return;
      }

      push(node, start, end);

      int middle = start + (end - start) / 2;

      update(node * 2, start, middle, left, right, value);
      update(node * 2 + 1, middle + 1, end, left, right, value);

      sums[node] = (sums[node * 2] + sums[node * 2 + 1]) % MOD;

      squareSums[node] = (squareSums[node * 2] + squareSums[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int start, int end, long value) {
      long count = end - start + 1L;

      squareSums[node] = (squareSums[node] + 2L * value * sums[node] + count * value * value) % MOD;

      sums[node] = (sums[node] + count * value) % MOD;

      lazy[node] += value;
    }

    private void push(int node, int start, int end) {
      if (lazy[node] == 0 || start == end) {
        return;
      }

      int middle = start + (end - start) / 2;

      apply(node * 2, start, middle, lazy[node]);

      apply(node * 2 + 1, middle + 1, end, lazy[node]);

      lazy[node] = 0;
    }
  }

  static void main() {
    System.out.println(sumCounts(new int[] {1, 2, 1})); // 15
    System.out.println(sumCounts(new int[] {2, 2})); // 3
    System.out.println(sumCounts(new int[] {1, 2, 3})); // 20
    System.out.println(sumCounts(new int[] {1, 1, 1})); // 6
    System.out.println(sumCounts(new int[] {1})); // 1
  }
}
