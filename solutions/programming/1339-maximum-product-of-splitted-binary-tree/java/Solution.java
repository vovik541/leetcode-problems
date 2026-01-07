package dev.vkh.solutions;

class Solution {
  private static final int MOD = 1_000_000_007;

  private long totalTreeSum = 0L;
  private long bestProduct = 0L;

  public int maxProduct(TreeNode root) {
    totalTreeSum = computeTotalSum(root);
    computeSubtreeSumsAndBest(root);

    return (int) (bestProduct % MOD);
  }

  private long computeTotalSum(TreeNode node) {
    if (node == null) return 0L;

    long leftSum = computeTotalSum(node.left);
    long rightSum = computeTotalSum(node.right);

    return leftSum + rightSum + node.val;
  }

  private long computeSubtreeSumsAndBest(TreeNode node) {
    if (node == null) return 0L;

    long leftSum = computeSubtreeSumsAndBest(node.left);
    long rightSum = computeSubtreeSumsAndBest(node.right);

    long subtreeSum = leftSum + rightSum + node.val;

    long productIfCutAboveThisSubtree = subtreeSum * (totalTreeSum - subtreeSum);
    if (productIfCutAboveThisSubtree > bestProduct) {
      bestProduct = productIfCutAboveThisSubtree;
    }

    return subtreeSum;
  }

  static void main(String[] args) {
    Solution solution = new Solution();

    TreeNode example1 =
        new TreeNode(
            1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, new TreeNode(6), null));
    int answer = solution.maxProduct(example1);
    System.out.println("expected 110, got " + answer);
  }
}

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
