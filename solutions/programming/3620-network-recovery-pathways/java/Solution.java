package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class Solution {

  private static class Edge {
    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;
      this.cost = cost;
    }
  }

  public static int findMaxPathScore(int[][] edges, boolean[] online, long k) {
    int nodeCount = online.length;

    List<Edge>[] graph = buildGraph(nodeCount, edges);
    List<Integer> topologicalOrder = buildTopologicalOrder(nodeCount, graph);

    int maxEdgeCost = 0;

    for (int[] edge : edges) {
      maxEdgeCost = Math.max(maxEdgeCost, edge[2]);
    }

    int left = 0;
    int right = maxEdgeCost;
    int bestScore = -1;

    while (left <= right) {
      int middleScore = left + (right - left) / 2;

      if (canReachWithScore(graph, topologicalOrder, online, k, middleScore)) {
        bestScore = middleScore;
        left = middleScore + 1;
      } else {
        right = middleScore - 1;
      }
    }

    return bestScore;
  }

  private static List<Edge>[] buildGraph(int nodeCount, int[][] edges) {
    List<Edge>[] graph = new ArrayList[nodeCount];

    for (int node = 0; node < nodeCount; node++) {
      graph[node] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int from = edge[0];
      int to = edge[1];
      int cost = edge[2];

      graph[from].add(new Edge(from, to, cost));
    }

    return graph;
  }

  private static List<Integer> buildTopologicalOrder(int nodeCount, List<Edge>[] graph) {
    int[] indegree = new int[nodeCount];

    for (int node = 0; node < nodeCount; node++) {
      for (Edge edge : graph[node]) {
        indegree[edge.to]++;
      }
    }

    Queue<Integer> queue = new ArrayDeque<>();

    for (int node = 0; node < nodeCount; node++) {
      if (indegree[node] == 0) {
        queue.offer(node);
      }
    }

    List<Integer> topologicalOrder = new ArrayList<>();

    while (!queue.isEmpty()) {
      int currentNode = queue.poll();
      topologicalOrder.add(currentNode);

      for (Edge edge : graph[currentNode]) {
        indegree[edge.to]--;

        if (indegree[edge.to] == 0) {
          queue.offer(edge.to);
        }
      }
    }

    return topologicalOrder;
  }

  private static boolean canReachWithScore(
      List<Edge>[] graph,
      List<Integer> topologicalOrder,
      boolean[] online,
      long k,
      int minimumAllowedEdgeCost) {
    int nodeCount = online.length;

    long infinity = Long.MAX_VALUE / 4;
    long[] minCost = new long[nodeCount];

    Arrays.fill(minCost, infinity);
    minCost[0] = 0;

    for (int currentNode : topologicalOrder) {
      if (minCost[currentNode] == infinity) {
        continue;
      }

      if (!online[currentNode]) {
        continue;
      }

      for (Edge edge : graph[currentNode]) {
        int nextNode = edge.to;

        if (!online[nextNode]) {
          continue;
        }

        if (edge.cost < minimumAllowedEdgeCost) {
          continue;
        }

        long newCost = minCost[currentNode] + edge.cost;

        if (newCost < minCost[nextNode]) {
          minCost[nextNode] = newCost;
        }
      }
    }

    return minCost[nodeCount - 1] <= k;
  }

  static void main() {
    int[][] edges = {
      {0, 1, 7},
      {1, 4, 5},
      {0, 2, 6},
      {2, 3, 6},
      {3, 4, 2},
      {2, 4, 6}
    };
    boolean[] online = {true, true, true, false, true};
    long k = 12;

    System.out.println(findMaxPathScore(edges, online, k)); // Output: 6
  }
}
