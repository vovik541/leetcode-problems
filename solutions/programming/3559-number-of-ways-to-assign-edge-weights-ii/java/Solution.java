package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class Solution {

  private static final int mod = 1_000_000_007;

  public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
    int nodeCount = edges.length + 1;

    List<Integer>[] graph = buildGraph(nodeCount, edges);

    int maxPower = 1;
    while ((1 << maxPower) <= nodeCount) {
      maxPower++;
    }

    int[][] parentByPower = new int[maxPower][nodeCount + 1];
    int[] depthByNode = new int[nodeCount + 1];

    buildDepthAndParents(graph, parentByPower, depthByNode);

    for (int power = 1; power < maxPower; power++) {
      for (int node = 1; node <= nodeCount; node++) {
        int middleParent = parentByPower[power - 1][node];
        parentByPower[power][node] = parentByPower[power - 1][middleParent];
      }
    }

    int[] powersOfTwo = buildPowersOfTwo(nodeCount);
    int[] answer = new int[queries.length];

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int firstNode = queries[queryIndex][0];
      int secondNode = queries[queryIndex][1];

      int lowestCommonAncestor =
          findLowestCommonAncestor(firstNode, secondNode, parentByPower, depthByNode);

      int pathLength =
          depthByNode[firstNode] + depthByNode[secondNode] - 2 * depthByNode[lowestCommonAncestor];

      if (pathLength == 0) {
        answer[queryIndex] = 0;
      } else {
        answer[queryIndex] = powersOfTwo[pathLength - 1];
      }
    }

    return answer;
  }

  private List<Integer>[] buildGraph(int nodeCount, int[][] edges) {
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

    return graph;
  }

  private void buildDepthAndParents(
      List<Integer>[] graph, int[][] parentByPower, int[] depthByNode) {
    Queue<Integer> nodesToVisit = new ArrayDeque<>();
    boolean[] visited = new boolean[depthByNode.length];

    nodesToVisit.offer(1);
    visited[1] = true;
    parentByPower[0][1] = 0;
    depthByNode[1] = 0;

    while (!nodesToVisit.isEmpty()) {
      int currentNode = nodesToVisit.poll();

      for (int nextNode : graph[currentNode]) {
        if (visited[nextNode]) {
          continue;
        }

        visited[nextNode] = true;
        parentByPower[0][nextNode] = currentNode;
        depthByNode[nextNode] = depthByNode[currentNode] + 1;
        nodesToVisit.offer(nextNode);
      }
    }
  }

  private int findLowestCommonAncestor(
      int firstNode, int secondNode, int[][] parentByPower, int[] depthByNode) {
    if (depthByNode[firstNode] < depthByNode[secondNode]) {
      int temporaryNode = firstNode;
      firstNode = secondNode;
      secondNode = temporaryNode;
    }

    int depthDifference = depthByNode[firstNode] - depthByNode[secondNode];

    for (int power = 0; power < parentByPower.length; power++) {
      if (((depthDifference >> power) & 1) == 1) {
        firstNode = parentByPower[power][firstNode];
      }
    }

    if (firstNode == secondNode) {
      return firstNode;
    }

    for (int power = parentByPower.length - 1; power >= 0; power--) {
      if (parentByPower[power][firstNode] != parentByPower[power][secondNode]) {
        firstNode = parentByPower[power][firstNode];
        secondNode = parentByPower[power][secondNode];
      }
    }

    return parentByPower[0][firstNode];
  }

  private int[] buildPowersOfTwo(int maxExponent) {
    int[] powersOfTwo = new int[maxExponent + 1];
    powersOfTwo[0] = 1;

    for (int exponent = 1; exponent <= maxExponent; exponent++) {
      powersOfTwo[exponent] = (int) ((long) powersOfTwo[exponent - 1] * 2 % mod);
    }

    return powersOfTwo;
  }

  private static void printArray(int[] array) {
    System.out.print("[");
    for (int index = 0; index < array.length; index++) {
      System.out.print(array[index]);
      if (index + 1 < array.length) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

  static void main() {
    Solution solution = new Solution();

    int[][] edges = {
      {1, 2},
      {1, 3},
      {3, 4},
      {3, 5}
    };
    int[][] queries = {
      {1, 4},
      {3, 4},
      {2, 5}
    };
    printArray(solution.assignEdgeWeights(edges, queries)); // [2, 1, 4]
  }
}
