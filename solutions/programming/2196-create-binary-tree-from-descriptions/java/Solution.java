package dev.vkh.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

  public TreeNode createBinaryTree(int[][] descriptions) {
    Map<Integer, TreeNode> nodeByValue = new HashMap<>();
    Set<Integer> childValues = new HashSet<>();

    for (int[] description : descriptions) {
      int parentValue = description[0];
      int childValue = description[1];
      int isLeftChild = description[2];

      TreeNode parentNode = nodeByValue.computeIfAbsent(parentValue, TreeNode::new);
      TreeNode childNode = nodeByValue.computeIfAbsent(childValue, TreeNode::new);

      if (isLeftChild == 1) {
        parentNode.left = childNode;
      } else {
        parentNode.right = childNode;
      }

      childValues.add(childValue);
    }

    for (int[] description : descriptions) {
      int parentValue = description[0];

      if (!childValues.contains(parentValue)) {
        return nodeByValue.get(parentValue);
      }
    }

    return null;
  }

  void main() {
    Solution solution = new Solution();

    int[][] descriptions1 = {
      {20, 15, 1},
      {20, 17, 0},
      {50, 20, 1},
      {50, 80, 0},
      {80, 19, 1}
    };
    TreeNode root = solution.createBinaryTree(descriptions1);
    System.out.println(root.val); // 50
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
