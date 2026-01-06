package medium.MaximumLevelSumOfBinaryTree;

import java.util.ArrayDeque;
import java.util.Queue;

class Solution {

    static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);

        System.out.println(new Solution().maxLevelSum(root)); // expected: 2
    }

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> nodesQueue = new ArrayDeque<>();
        nodesQueue.add(root);

        int currentLevel = 0;
        int bestLevel = 1;

        long bestLevelSum = Long.MIN_VALUE;

        while (!nodesQueue.isEmpty()) {
            currentLevel++;

            int nodesInThisLevel = nodesQueue.size();
            long levelSum = 0;

            for (int i = 0; i < nodesInThisLevel; i++) {
                TreeNode node = nodesQueue.poll();
                levelSum += node.val;

                if (node.left != null) {
                    nodesQueue.add(node.left);
                }
                if (node.right != null) {
                    nodesQueue.add(node.right);
                }
            }

            if (levelSum > bestLevelSum) {
                bestLevelSum = levelSum;
                bestLevel = currentLevel;
            }
        }

        return bestLevel;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}