package dev.vkh.solutions;

public class Solution {
  public int sumRootToLeaf(TreeNode root) {
    return calculateSumFromNode(root, 0);
  }

  private int calculateSumFromNode(TreeNode currentNode, int currentBinaryValue) {
    if (currentNode == null) {
      return 0;
    }

    int updatedBinaryValue = (currentBinaryValue << 1) | currentNode.val;

    if (currentNode.left == null && currentNode.right == null) {
      return updatedBinaryValue;
    }

    int leftSubtreeSum = calculateSumFromNode(currentNode.left, updatedBinaryValue);
    int rightSubtreeSum = calculateSumFromNode(currentNode.right, updatedBinaryValue);

    return leftSubtreeSum + rightSubtreeSum;
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }
}
