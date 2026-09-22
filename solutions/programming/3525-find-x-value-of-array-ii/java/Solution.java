package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static int[] resultArray(int[] nums, int k, int[][] queries) {
    SegmentTree segmentTree = new SegmentTree(nums, k);
    int[] result = new int[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int index = queries[queryIndex][0];
      int value = queries[queryIndex][1];
      int start = queries[queryIndex][2];
      int targetRemainder = queries[queryIndex][3];

      segmentTree.update(index, value);

      Node queryResult = segmentTree.query(start, nums.length - 1);

      result[queryIndex] = queryResult.prefixCount[targetRemainder];
    }

    return result;
  }

  private static class SegmentTree {

    private final int k;
    private final int size;
    private final Node[] tree;

    SegmentTree(int[] nums, int k) {
      this.k = k;

      int treeSize = 1;

      while (treeSize < nums.length) {
        treeSize *= 2;
      }

      size = treeSize;
      tree = new Node[size * 2];

      for (int index = 0; index < tree.length; index++) {
        tree[index] = new Node(k);
      }

      for (int index = 0; index < nums.length; index++) {
        setLeaf(size + index, nums[index]);
      }

      for (int index = size - 1; index >= 1; index--) {
        tree[index] = merge(tree[index * 2], tree[index * 2 + 1]);
      }
    }

    void update(int index, int value) {
      int treeIndex = size + index;

      setLeaf(treeIndex, value);

      treeIndex /= 2;

      while (treeIndex >= 1) {
        tree[treeIndex] = merge(tree[treeIndex * 2], tree[treeIndex * 2 + 1]);

        treeIndex /= 2;
      }
    }

    Node query(int left, int right) {
      left += size;
      right += size;

      Node leftResult = null;
      Node rightResult = null;

      while (left <= right) {
        if ((left & 1) == 1) {
          leftResult = leftResult == null ? tree[left] : merge(leftResult, tree[left]);

          left++;
        }

        if ((right & 1) == 0) {
          rightResult = rightResult == null ? tree[right] : merge(tree[right], rightResult);

          right--;
        }

        left /= 2;
        right /= 2;
      }

      if (leftResult == null) {
        return rightResult;
      }

      if (rightResult == null) {
        return leftResult;
      }

      return merge(leftResult, rightResult);
    }

    private void setLeaf(int treeIndex, int value) {
      Node node = new Node(k);
      int remainder = value % k;

      node.product = remainder;
      node.prefixCount[remainder] = 1;

      tree[treeIndex] = node;
    }

    private Node merge(Node leftNode, Node rightNode) {
      Node mergedNode = new Node(k);

      mergedNode.product = (leftNode.product * rightNode.product) % k;

      for (int remainder = 0; remainder < k; remainder++) {
        mergedNode.prefixCount[remainder] += leftNode.prefixCount[remainder];
      }

      for (int rightRemainder = 0; rightRemainder < k; rightRemainder++) {

        int combinedRemainder = (leftNode.product * rightRemainder) % k;

        mergedNode.prefixCount[combinedRemainder] += rightNode.prefixCount[rightRemainder];
      }

      return mergedNode;
    }
  }

  private static class Node {

    int product;
    int[] prefixCount;

    Node(int k) {
      product = 1 % k;
      prefixCount = new int[k];
    }
  }

  static void main() {
    System.out.println(
        Arrays.toString(
            resultArray(
                new int[] {1, 2, 3, 4, 5},
                3,
                new int[][] {
                  {2, 2, 0, 2},
                  {3, 3, 3, 0},
                  {0, 1, 0, 1}
                })));
    // [2, 2, 2]
    System.out.println(
        Arrays.toString(
            resultArray(
                new int[] {1, 2, 4, 8, 16, 32},
                4,
                new int[][] {
                  {0, 2, 0, 2},
                  {0, 2, 0, 1}
                })));
    // [1, 0]
    System.out.println(
        Arrays.toString(resultArray(new int[] {1, 1, 2, 1, 1}, 2, new int[][] {{2, 1, 0, 1}})));
    // [5]
  }
}
