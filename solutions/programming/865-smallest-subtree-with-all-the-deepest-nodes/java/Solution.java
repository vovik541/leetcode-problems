package dev.vkh.solutions;

/** Definition for a binary tree node. */
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

public class Solution {

  private static class SubtreeResult {
    private final TreeNode smallestSubtreeRoot;
    private final int deepestDepthFromHere;

    private SubtreeResult(TreeNode smallestSubtreeRoot, int deepestDepthFromHere) {
      this.smallestSubtreeRoot = smallestSubtreeRoot;
      this.deepestDepthFromHere = deepestDepthFromHere;
    }
  }

  public TreeNode subtreeWithAllDeepest(TreeNode root) {
    return computeSubtreeResult(root).smallestSubtreeRoot;
  }

  private SubtreeResult computeSubtreeResult(TreeNode currentNode) {
    if (currentNode == null) {
      return new SubtreeResult(null, 0);
    }

    SubtreeResult leftResult = computeSubtreeResult(currentNode.left);
    SubtreeResult rightResult = computeSubtreeResult(currentNode.right);

    if (leftResult.deepestDepthFromHere > rightResult.deepestDepthFromHere) {
      return new SubtreeResult(leftResult.smallestSubtreeRoot, leftResult.deepestDepthFromHere + 1);
    }

    if (rightResult.deepestDepthFromHere > leftResult.deepestDepthFromHere) {
      return new SubtreeResult(
          rightResult.smallestSubtreeRoot, rightResult.deepestDepthFromHere + 1);
    }

    return new SubtreeResult(currentNode, leftResult.deepestDepthFromHere + 1);
  }

  static void main(String[] args) {
    TreeNode root =
        new TreeNode(
            3,
            new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
            new TreeNode(1, new TreeNode(0), new TreeNode(8)));

    Solution solution = new Solution();
    TreeNode result = solution.subtreeWithAllDeepest(root);

    System.out.println("Result subtree root value: " + result.val);
  }
}
