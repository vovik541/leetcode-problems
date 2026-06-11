package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {

  private static final int mod = 1_000_000_007;

  public int assignEdgeWeights(int[][] edges) {
    int nodeCount = edges.length + 1;

    List<Integer>[] graph = new ArrayList[nodeCount + 1];
    for (int node = 1; node <= nodeCount; node++) {
      graph[node] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int firstNode = edge[0];
      int secondNode = edge[1];

      graph[firstNode].add(secondNode);
      graph[secondNode].add(firstNode);
    }

    int maximumDepth = calculateMaximumDepth(graph, nodeCount);

    return modularPower(2, maximumDepth - 1);
  }

  private int calculateMaximumDepth(List<Integer>[] graph, int nodeCount) {
    boolean[] visited = new boolean[nodeCount + 1];
    Queue<int[]> nodesToVisit = new ArrayDeque<>();

    nodesToVisit.offer(new int[] {1, 0});
    visited[1] = true;

    int maximumDepth = 0;

    while (!nodesToVisit.isEmpty()) {
      int[] currentState = nodesToVisit.poll();
      int currentNode = currentState[0];
      int currentDepth = currentState[1];

      maximumDepth = Math.max(maximumDepth, currentDepth);

      for (int nextNode : graph[currentNode]) {
        if (visited[nextNode]) {
          continue;
        }

        visited[nextNode] = true;
        nodesToVisit.offer(new int[] {nextNode, currentDepth + 1});
      }
    }

    return maximumDepth;
  }

  private int modularPower(int base, int exponent) {
    long result = 1L;
    long currentBase = base;

    while (exponent > 0) {
      if ((exponent & 1) == 1) {
        result = (result * currentBase) % mod;
      }

      currentBase = (currentBase * currentBase) % mod;
      exponent >>= 1;
    }

    return (int) result;
  }

  static void main() {
    Solution solution = new Solution();

    int[][] edges = {
      {1, 2},
      {1, 3},
      {3, 4},
      {3, 5}
    };
    System.out.println(solution.assignEdgeWeights(edges)); // 2
  }
}
