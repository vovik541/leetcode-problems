package dev.vkh.solutions;

class Solution {

  private int matchingNodes = 0;

  public int averageOfSubtree(TreeNode root) {
    calculateSubtree(root);
    return matchingNodes;
  }

  private int[] calculateSubtree(TreeNode node) {
    if (node == null) {
      return new int[] {0, 0};
    }

    int[] leftSubtree = calculateSubtree(node.left);
    int[] rightSubtree = calculateSubtree(node.right);

    int subtreeSum = leftSubtree[0] + rightSubtree[0] + node.val;
    int subtreeSize = leftSubtree[1] + rightSubtree[1] + 1;

    if (subtreeSum / subtreeSize == node.val) {
      matchingNodes++;
    }

    return new int[] {subtreeSum, subtreeSize};
  }

  static void main() {
    TreeNode root =
        new TreeNode(
            4,
            new TreeNode(8, new TreeNode(0), new TreeNode(1)),
            new TreeNode(5, null, new TreeNode(6)));

    System.out.println(new Solution().averageOfSubtree(root)); // 5

    TreeNode singleNode = new TreeNode(1);

    System.out.println(new Solution().averageOfSubtree(singleNode)); // 1
  }

  static class TreeNode {
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
