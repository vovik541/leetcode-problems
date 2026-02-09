package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  public TreeNode balanceBST(TreeNode root) {
    List<TreeNode> inorderNodes = new ArrayList<>();
    collectInorderNodes(root, inorderNodes);
    return buildBalancedFromSortedNodes(inorderNodes, 0, inorderNodes.size() - 1);
  }

  private void collectInorderNodes(TreeNode currentNode, List<TreeNode> inorderNodes) {
    if (currentNode == null) {
      return;
    }

    collectInorderNodes(currentNode.left, inorderNodes);
    inorderNodes.add(currentNode);
    collectInorderNodes(currentNode.right, inorderNodes);
  }

  private TreeNode buildBalancedFromSortedNodes(
      List<TreeNode> inorderNodes, int leftIndex, int rightIndex) {
    if (leftIndex > rightIndex) {
      return null;
    }

    int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
    TreeNode rootNode = inorderNodes.get(middleIndex);

    rootNode.left = buildBalancedFromSortedNodes(inorderNodes, leftIndex, middleIndex - 1);
    rootNode.right = buildBalancedFromSortedNodes(inorderNodes, middleIndex + 1, rightIndex);

    return rootNode;
  }

  private class TreeNode {
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
