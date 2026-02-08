package dev.vkh.solutions;

class Solution {

  public boolean isBalanced(TreeNode root) {
    return calculateHeightOrUnbalanced(root) != -1;
  }

  private int calculateHeightOrUnbalanced(TreeNode currentNode) {
    if (currentNode == null) {
      return 0;
    }

    int leftSubtreeHeight = calculateHeightOrUnbalanced(currentNode.left);
    if (leftSubtreeHeight == -1) {
      return -1;
    }

    int rightSubtreeHeight = calculateHeightOrUnbalanced(currentNode.right);
    if (rightSubtreeHeight == -1) {
      return -1;
    }

    if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1) {
      return -1;
    }

    return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
  }

  class TreeNode {
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
