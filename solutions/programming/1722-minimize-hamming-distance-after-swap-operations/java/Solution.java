package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
    int arrayLength = source.length;
    UnionFind unionFind = new UnionFind(arrayLength);

    for (int[] allowedSwap : allowedSwaps) {
      unionFind.union(allowedSwap[0], allowedSwap[1]);
    }

    Map<Integer, Map<Integer, Integer>> componentToValueCounts = new HashMap<>();

    for (int index = 0; index < arrayLength; index++) {
      int root = unionFind.find(index);

      componentToValueCounts
          .computeIfAbsent(root, ignored -> new HashMap<>())
          .merge(source[index], 1, Integer::sum);
    }

    int minimumHammingDistance = 0;

    for (int index = 0; index < arrayLength; index++) {
      int root = unionFind.find(index);
      Map<Integer, Integer> valueCounts = componentToValueCounts.get(root);
      int targetValue = target[index];

      int availableCount = valueCounts.getOrDefault(targetValue, 0);

      if (availableCount > 0) {
        if (availableCount == 1) {
          valueCounts.remove(targetValue);
        } else {
          valueCounts.put(targetValue, availableCount - 1);
        }
      } else {
        minimumHammingDistance++;
      }
    }

    return minimumHammingDistance;
  }

  private static class UnionFind {
    private final int[] parent;
    private final int[] rank;

    private UnionFind(int size) {
      parent = new int[size];
      rank = new int[size];

      for (int index = 0; index < size; index++) {
        parent[index] = index;
      }
    }

    private int find(int node) {
      if (parent[node] != node) {
        parent[node] = find(parent[node]);
      }

      return parent[node];
    }

    private void union(int firstNode, int secondNode) {
      int firstRoot = find(firstNode);
      int secondRoot = find(secondNode);

      if (firstRoot == secondRoot) {
        return;
      }

      if (rank[firstRoot] < rank[secondRoot]) {
        parent[firstRoot] = secondRoot;
      } else if (rank[firstRoot] > rank[secondRoot]) {
        parent[secondRoot] = firstRoot;
      } else {
        parent[secondRoot] = firstRoot;
        rank[firstRoot]++;
      }
    }
  }

  static void main() {
    System.out.println(
        new Solution()
            .minimumHammingDistance(
                new int[] {1, 2, 3, 4}, new int[] {2, 1, 4, 5}, new int[][] {{0, 1}, {2, 3}}));
  }
}
