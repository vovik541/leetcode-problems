package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {

  public int countCompleteComponents(int n, int[][] edges) {
    List<Integer>[] graph = new ArrayList[n];

    for (int node = 0; node < n; node++) {
      graph[node] = new ArrayList<>();
    }

    for (int[] edge : edges) {
      int firstNode = edge[0];
      int secondNode = edge[1];

      graph[firstNode].add(secondNode);
      graph[secondNode].add(firstNode);
    }

    boolean[] visited = new boolean[n];
    int completeComponents = 0;

    for (int startNode = 0; startNode < n; startNode++) {
      if (visited[startNode]) {
        continue;
      }

      int nodeCount = 0;
      int degreeSum = 0;

      Queue<Integer> queue = new ArrayDeque<>();
      queue.offer(startNode);
      visited[startNode] = true;

      while (!queue.isEmpty()) {
        int currentNode = queue.poll();

        nodeCount++;
        degreeSum += graph[currentNode].size();

        for (int nextNode : graph[currentNode]) {
          if (!visited[nextNode]) {
            visited[nextNode] = true;
            queue.offer(nextNode);
          }
        }
      }

      int edgeCount = degreeSum / 2;
      int expectedEdgeCount = nodeCount * (nodeCount - 1) / 2;

      if (edgeCount == expectedEdgeCount) {
        completeComponents++;
      }
    }

    return completeComponents;
  }

  static void main() {
    Solution solution = new Solution();

    int n1 = 6;
    int[][] edges1 = {
      {0, 1},
      {0, 2},
      {1, 2},
      {3, 4}
    };

    System.out.println(solution.countCompleteComponents(n1, edges1));
    // Output: 3

    int n2 = 6;
    int[][] edges2 = {
      {0, 1},
      {0, 2},
      {1, 2},
      {3, 4},
      {3, 5}
    };

    System.out.println(solution.countCompleteComponents(n2, edges2));
    // Output: 1
  }
}
