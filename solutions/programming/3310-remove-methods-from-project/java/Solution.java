package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution {

  public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
    List<Integer>[] graph = new ArrayList[n];

    for (int method = 0; method < n; method++) {
      graph[method] = new ArrayList<>();
    }

    for (int[] invocation : invocations) {
      int from = invocation[0];
      int to = invocation[1];

      graph[from].add(to);
    }

    boolean[] suspicious = new boolean[n];

    markSuspiciousMethods(k, graph, suspicious);

    for (int[] invocation : invocations) {
      int from = invocation[0];
      int to = invocation[1];

      if (!suspicious[from] && suspicious[to]) {
        return buildAllMethods(n);
      }
    }

    List<Integer> remainingMethods = new ArrayList<>();

    for (int method = 0; method < n; method++) {
      if (!suspicious[method]) {
        remainingMethods.add(method);
      }
    }

    return remainingMethods;
  }

  private void markSuspiciousMethods(int startMethod, List<Integer>[] graph, boolean[] suspicious) {
    Queue<Integer> queue = new ArrayDeque<>();

    suspicious[startMethod] = true;
    queue.offer(startMethod);

    while (!queue.isEmpty()) {
      int currentMethod = queue.poll();

      for (int nextMethod : graph[currentMethod]) {
        if (!suspicious[nextMethod]) {
          suspicious[nextMethod] = true;
          queue.offer(nextMethod);
        }
      }
    }
  }

  private List<Integer> buildAllMethods(int n) {
    List<Integer> allMethods = new ArrayList<>();

    for (int method = 0; method < n; method++) {
      allMethods.add(method);
    }

    return allMethods;
  }

  static void main() {
    Solution solution = new Solution();

    int n1 = 4;
    int k1 = 1;
    int[][] invocations1 = {
      {1, 2},
      {0, 1},
      {3, 2}
    };
    System.out.println(solution.remainingMethods(n1, k1, invocations1));
    //  [0, 1, 2, 3]

    int n2 = 5;
    int k2 = 0;
    int[][] invocations2 = {
      {1, 2},
      {0, 2},
      {0, 1},
      {3, 4}
    };
    System.out.println(solution.remainingMethods(n2, k2, invocations2));
    // [3, 4]

    int n3 = 3;
    int k3 = 2;
    int[][] invocations3 = {
      {1, 2},
      {0, 1},
      {2, 0}
    };
    System.out.println(solution.remainingMethods(n3, k3, invocations3));
    // []
  }
}
