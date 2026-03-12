package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  public int maxStability(int n, int[][] edges, int k) {
    if (!canBuildSpanningTreeWithThreshold(n, edges, k, 1)) {
      return -1;
    }

    int left = 1;
    int right = 200_000;
    int bestStability = 1;

    while (left <= right) {
      int middleStability = left + (right - left) / 2;

      if (canBuildSpanningTreeWithThreshold(n, edges, k, middleStability)) {
        bestStability = middleStability;
        left = middleStability + 1;
      } else {
        right = middleStability - 1;
      }
    }

    return bestStability;
  }

  private boolean canBuildSpanningTreeWithThreshold(
      int n, int[][] edges, int maxUpgrades, int targetStability) {
    DisjointSetUnion disjointSetUnion = new DisjointSetUnion(n);

    List<int[]> optionalEdgesWithoutUpgrade = new ArrayList<>();
    List<int[]> optionalEdgesWithUpgrade = new ArrayList<>();

    for (int[] edge : edges) {
      int fromNode = edge[0];
      int toNode = edge[1];
      int strength = edge[2];
      int isMandatory = edge[3];

      if (isMandatory == 1) {
        if (strength < targetStability) {
          return false;
        }

        if (!disjointSetUnion.union(fromNode, toNode)) {
          return false;
        }
      } else {
        if (strength >= targetStability) {
          optionalEdgesWithoutUpgrade.add(edge);
        } else if (strength * 2 >= targetStability) {
          optionalEdgesWithUpgrade.add(edge);
        }
      }
    }

    for (int[] edge : optionalEdgesWithoutUpgrade) {
      disjointSetUnion.union(edge[0], edge[1]);
    }

    int upgradesUsed = 0;
    for (int[] edge : optionalEdgesWithUpgrade) {
      if (disjointSetUnion.union(edge[0], edge[1])) {
        upgradesUsed++;
        if (upgradesUsed > maxUpgrades) {
          return false;
        }
      }
    }

    return disjointSetUnion.getComponentCount() == 1;
  }

  private static class DisjointSetUnion {
    private final int[] parentByNode;
    private final int[] sizeByRoot;
    private int componentCount;

    private DisjointSetUnion(int nodeCount) {
      this.parentByNode = new int[nodeCount];
      this.sizeByRoot = new int[nodeCount];
      this.componentCount = nodeCount;

      for (int node = 0; node < nodeCount; node++) {
        parentByNode[node] = node;
        sizeByRoot[node] = 1;
      }
    }

    private int find(int node) {
      if (parentByNode[node] != node) {
        parentByNode[node] = find(parentByNode[node]);
      }
      return parentByNode[node];
    }

    private boolean union(int firstNode, int secondNode) {
      int firstRoot = find(firstNode);
      int secondRoot = find(secondNode);

      if (firstRoot == secondRoot) {
        return false;
      }

      if (sizeByRoot[firstRoot] < sizeByRoot[secondRoot]) {
        int temporaryRoot = firstRoot;
        firstRoot = secondRoot;
        secondRoot = temporaryRoot;
      }

      parentByNode[secondRoot] = firstRoot;
      sizeByRoot[firstRoot] += sizeByRoot[secondRoot];
      componentCount--;

      return true;
    }

    private int getComponentCount() {
      return componentCount;
    }
  }

  static void main() {
    Solution solution = new Solution();

    int[][] edges = {
      {0, 1, 2, 1},
      {1, 2, 3, 0}
    };
    System.out.println(solution.maxStability(3, edges, 1)); // 2

    edges =
        new int[][] {
          {0, 1, 4, 0},
          {1, 2, 3, 0},
          {0, 2, 1, 0}
        };
    System.out.println(solution.maxStability(3, edges, 2)); // 6

    edges =
        new int[][] {
          {0, 1, 1, 1},
          {1, 2, 1, 1},
          {2, 0, 1, 1}
        };
    System.out.println(solution.maxStability(3, edges, 0)); // -1
  }
}
