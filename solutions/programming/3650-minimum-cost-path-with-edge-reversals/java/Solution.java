package dev.vkh.solutions;

import java.util.*;

public class Solution {

  private static final class Edge {
    final int toNode;
    final int cost;

    Edge(int toNode, int cost) {
      this.toNode = toNode;
      this.cost = cost;
    }
  }

  private static final class State {
    final int node;
    final long distance;

    State(int node, long distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public int minCost(int n, int[][] edges) {
    List<List<Edge>> adjacencyList = new ArrayList<>(n);
    for (int node = 0; node < n; node++) {
      adjacencyList.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      int fromNode = edge[0];
      int toNode = edge[1];
      int weight = edge[2];

      adjacencyList.get(fromNode).add(new Edge(toNode, weight));
      adjacencyList.get(toNode).add(new Edge(fromNode, 2 * weight));
    }

    long[] minDistance = new long[n];
    Arrays.fill(minDistance, Long.MAX_VALUE);
    minDistance[0] = 0L;

    PriorityQueue<State> minHeap = new PriorityQueue<>(Comparator.comparingLong(s -> s.distance));
    minHeap.add(new State(0, 0L));

    while (!minHeap.isEmpty()) {
      State current = minHeap.poll();
      int currentNode = current.node;
      long currentDistance = current.distance;

      if (currentDistance != minDistance[currentNode]) {
        continue;
      }

      if (currentNode == n - 1) {
        return (int) currentDistance;
      }

      for (Edge edge : adjacencyList.get(currentNode)) {
        int nextNode = edge.toNode;
        long newDistance = currentDistance + edge.cost;

        if (newDistance < minDistance[nextNode]) {
          minDistance[nextNode] = newDistance;
          minHeap.add(new State(nextNode, newDistance));
        }
      }
    }

    return -1;
  }

  static void main() {
    Solution solution = new Solution();

    int n = 4;
    int[][] edges = {{0, 1, 3}, {3, 1, 1}, {2, 3, 4}, {0, 2, 2}};

//    int n = 4;
//    int[][] edges = {{0, 2, 1}, {2, 1, 1}, {1, 3, 1}, {2, 3, 3}};
    System.out.println(solution.minCost(n, edges));
  }
}
