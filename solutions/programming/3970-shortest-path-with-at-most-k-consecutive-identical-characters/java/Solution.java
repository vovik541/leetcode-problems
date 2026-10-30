package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static int shortestPath(int n, int[][] edges, String labels, int k) {

    List<int[]>[] graph = new ArrayList[n];

    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      graph[edge[0]].add(new int[] {edge[1], edge[2]});
    }

    int[][] distances = new int[n][k + 1];

    for (int[] distance : distances) {
      Arrays.fill(distance, Integer.MAX_VALUE);
    }

    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(state -> state[0]));

    distances[0][1] = 0;
    queue.offer(new int[] {0, 0, 1});

    while (!queue.isEmpty()) {
      int[] state = queue.poll();

      int distance = state[0];
      int node = state[1];
      int count = state[2];

      if (distance != distances[node][count]) {
        continue;
      }

      if (node == n - 1) {
        return distance;
      }

      for (int[] edge : graph[node]) {
        int neighbor = edge[0];
        int weight = edge[1];

        int nextCount = labels.charAt(node) == labels.charAt(neighbor) ? count + 1 : 1;

        if (nextCount > k) {
          continue;
        }

        int nextDistance = distance + weight;

        if (nextDistance < distances[neighbor][nextCount]) {
          distances[neighbor][nextCount] = nextDistance;

          queue.offer(new int[] {nextDistance, neighbor, nextCount});
        }
      }
    }

    return -1;
  }

  static void main() {
    System.out.println(
        shortestPath(3, new int[][] {{0, 1, 1}, {1, 2, 1}, {0, 2, 3}}, "aab", 1)); // 3
    System.out.println(
        shortestPath(3, new int[][] {{0, 1, 1}, {1, 2, 1}, {0, 2, 3}}, "aab", 2)); // 2
    System.out.println(shortestPath(3, new int[][] {{0, 1, 1}, {1, 2, 1}}, "aaa", 2)); // -1
    System.out.println(shortestPath(1, new int[][] {}, "a", 1)); // 0
    System.out.println(
        shortestPath(
            4,
            new int[][] {
              {0, 1, 1},
              {1, 2, 1},
              {2, 3, 1},
              {0, 3, 10}
            },
            "aaab",
            2)); // 10
  }
}
