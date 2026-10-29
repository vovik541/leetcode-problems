package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

  public static List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

    int[] head = new int[n];
    Arrays.fill(head, -1);

    int[] to = new int[2 * (n - 1)];
    int[] next = new int[2 * (n - 1)];
    int edgeCount = 0;

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];

      to[edgeCount] = v;
      next[edgeCount] = head[u];
      head[u] = edgeCount++;

      to[edgeCount] = u;
      next[edgeCount] = head[v];
      head[v] = edgeCount++;
    }

    int[] parent = new int[n];
    int[] depth = new int[n];
    int[] size = new int[n];
    int[] heavy = new int[n];
    int[] order = new int[n];

    Arrays.fill(parent, -1);
    Arrays.fill(heavy, -1);

    int[] stack = new int[n];
    int stackSize = 0;
    int orderSize = 0;

    stack[stackSize++] = 0;

    while (stackSize > 0) {
      int node = stack[--stackSize];
      order[orderSize++] = node;

      for (int edge = head[node]; edge != -1; edge = next[edge]) {
        int neighbor = to[edge];

        if (neighbor == parent[node]) {
          continue;
        }

        parent[neighbor] = node;
        depth[neighbor] = depth[node] + 1;
        stack[stackSize++] = neighbor;
      }
    }

    for (int i = n - 1; i >= 0; i--) {
      int node = order[i];
      size[node] = 1;

      int largestSize = 0;

      for (int edge = head[node]; edge != -1; edge = next[edge]) {
        int neighbor = to[edge];

        if (parent[neighbor] != node) {
          continue;
        }

        size[node] += size[neighbor];

        if (size[neighbor] > largestSize) {
          largestSize = size[neighbor];
          heavy[node] = neighbor;
        }
      }
    }

    int[] chainHead = new int[n];
    int[] position = new int[n];

    int currentPosition = 0;
    stackSize = 0;
    stack[stackSize++] = 0;

    while (stackSize > 0) {
      int start = stack[--stackSize];

      for (int node = start; node != -1; node = heavy[node]) {
        chainHead[node] = start;
        position[node] = currentPosition++;

        for (int edge = head[node]; edge != -1; edge = next[edge]) {
          int neighbor = to[edge];

          if (parent[neighbor] == node && neighbor != heavy[node]) {
            stack[stackSize++] = neighbor;
          }
        }
      }
    }

    FenwickTree fenwick = new FenwickTree(n);
    char[] characters = s.toCharArray();

    for (int node = 0; node < n; node++) {
      fenwick.update(position[node], 1 << (characters[node] - 'a'));
    }

    List<Boolean> result = new ArrayList<>();

    for (String query : queries) {
      String[] parts = query.split(" ");

      if (parts[0].equals("update")) {
        int node = Integer.parseInt(parts[1]);
        char newCharacter = parts[2].charAt(0);

        int oldMask = 1 << (characters[node] - 'a');
        int newMask = 1 << (newCharacter - 'a');

        fenwick.update(position[node], oldMask ^ newMask);
        characters[node] = newCharacter;

      } else {
        int u = Integer.parseInt(parts[1]);
        int v = Integer.parseInt(parts[2]);

        int mask = 0;

        while (chainHead[u] != chainHead[v]) {
          if (depth[chainHead[u]] < depth[chainHead[v]]) {
            int temp = u;
            u = v;
            v = temp;
          }

          mask ^= fenwick.rangeXor(position[chainHead[u]], position[u]);

          u = parent[chainHead[u]];
        }

        if (depth[u] > depth[v]) {
          int temp = u;
          u = v;
          v = temp;
        }

        mask ^= fenwick.rangeXor(position[u], position[v]);

        result.add((mask & (mask - 1)) == 0);
      }
    }

    return result;
  }

  private static class FenwickTree {

    private final int[] tree;

    FenwickTree(int size) {
      tree = new int[size + 1];
    }

    void update(int index, int value) {
      for (int i = index + 1; i < tree.length; i += i & -i) {
        tree[i] ^= value;
      }
    }

    int prefixXor(int index) {
      int result = 0;

      for (int i = index + 1; i > 0; i -= i & -i) {
        result ^= tree[i];
      }

      return result;
    }

    int rangeXor(int left, int right) {
      return prefixXor(right) ^ prefixXor(left - 1);
    }
  }

  static void main() {
    System.out.println(
        palindromePath(
            3,
            new int[][] {{0, 1}, {1, 2}},
            "aac",
            new String[] {"query 0 2", "update 1 b", "query 0 2"})); // [true, false]
    System.out.println(
        palindromePath(
            4,
            new int[][] {{0, 1}, {0, 2}, {0, 3}},
            "abca",
            new String[] {
              "query 1 2", "update 0 b", "query 2 3", "update 3 a", "query 1 3"
            })); // [false, false, true]
    System.out.println(
        palindromePath(
            1,
            new int[][] {},
            "a",
            new String[] {"query 0 0", "update 0 z", "query 0 0"})); // [true, true]
  }
}
