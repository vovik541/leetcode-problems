package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  private static class Road {
    int to;
    int distance;

    Road(int to, int distance) {
      this.to = to;
      this.distance = distance;
    }
  }

  public static int minScore(int n, int[][] roads) {
    List<Road>[] graph = new ArrayList[n + 1];

    for (int city = 1; city <= n; city++) {
      graph[city] = new ArrayList<>();
    }

    for (int[] road : roads) {
      int cityA = road[0];
      int cityB = road[1];
      int distance = road[2];

      graph[cityA].add(new Road(cityB, distance));
      graph[cityB].add(new Road(cityA, distance));
    }

    boolean[] visited = new boolean[n + 1];

    return dfs(1, graph, visited);
  }

  private static int dfs(int city, List<Road>[] graph, boolean[] visited) {
    visited[city] = true;

    int minimumDistance = Integer.MAX_VALUE;

    for (Road road : graph[city]) {
      minimumDistance = Math.min(minimumDistance, road.distance);

      if (!visited[road.to]) {
        minimumDistance = Math.min(minimumDistance, dfs(road.to, graph, visited));
      }
    }

    return minimumDistance;
  }

  static void main() {
    int n = 4;
    int[][] roads = {
      {1, 2, 9},
      {2, 3, 6},
      {2, 4, 5},
      {1, 4, 7}
    };
    System.out.println(minScore(n, roads)); // Output: 5
  }
}
