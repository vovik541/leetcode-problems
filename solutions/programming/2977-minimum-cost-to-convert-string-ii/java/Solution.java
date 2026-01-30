package dev.vkh.solutions;

import java.util.*;

public class Solution {
  private static final long INF = Long.MAX_VALUE / 4;

  private static final class TrieNode {
    TrieNode[] next = new TrieNode[26];
    int wordId = -1;
  }

  private static TrieNode buildTrie(List<String> uniqueStrings, Map<String, Integer> stringToId) {
    TrieNode root = new TrieNode();
    for (String word : uniqueStrings) {
      int id = stringToId.get(word);
      TrieNode node = root;
      for (int idx = 0; idx < word.length(); idx++) {
        int ch = word.charAt(idx) - 'a';
        if (node.next[ch] == null) {
          node.next[ch] = new TrieNode();
        }
        node = node.next[ch];
      }
      node.wordId = id;
    }
    return root;
  }

  public long minimumCost(
      String source, String target, String[] original, String[] changed, int[] cost) {
    int rulesCount = cost.length;

    Map<String, Integer> stringToId = new HashMap<>();
    List<String> uniqueStrings = new ArrayList<>();

    for (int i = 0; i < rulesCount; i++) {
      if (!stringToId.containsKey(original[i])) {
        stringToId.put(original[i], uniqueStrings.size());
        uniqueStrings.add(original[i]);
      }
      if (!stringToId.containsKey(changed[i])) {
        stringToId.put(changed[i], uniqueStrings.size());
        uniqueStrings.add(changed[i]);
      }
    }

    int uniqueCount = uniqueStrings.size();

    long[][] minCostBetween = new long[uniqueCount][uniqueCount];
    for (int i = 0; i < uniqueCount; i++) {
      Arrays.fill(minCostBetween[i], INF);
      minCostBetween[i][i] = 0;
    }

    for (int i = 0; i < rulesCount; i++) {
      int fromId = stringToId.get(original[i]);
      int toId = stringToId.get(changed[i]);
      minCostBetween[fromId][toId] = Math.min(minCostBetween[fromId][toId], cost[i]);
    }

    for (int k = 0; k < uniqueCount; k++) {
      for (int i = 0; i < uniqueCount; i++) {
        if (minCostBetween[i][k] == INF) continue;
        for (int j = 0; j < uniqueCount; j++) {
          if (minCostBetween[k][j] == INF) continue;
          long through = minCostBetween[i][k] + minCostBetween[k][j];
          if (through < minCostBetween[i][j]) {
            minCostBetween[i][j] = through;
          }
        }
      }
    }

    TrieNode trieRoot = buildTrie(uniqueStrings, stringToId);

    int n = source.length();
    long[] dp = new long[n + 1];
    Arrays.fill(dp, INF);
    dp[0] = 0;

    for (int startIndex = 0; startIndex < n; startIndex++) {
      if (dp[startIndex] == INF) continue;

      if (startIndex < n && source.charAt(startIndex) == target.charAt(startIndex)) {
        dp[startIndex + 1] = Math.min(dp[startIndex + 1], dp[startIndex]);
      }

      TrieNode sourceNode = trieRoot;
      TrieNode targetNode = trieRoot;

      for (int endIndex = startIndex; endIndex < n; endIndex++) {
        int sourceChar = source.charAt(endIndex) - 'a';
        int targetChar = target.charAt(endIndex) - 'a';

        sourceNode = (sourceNode == null) ? null : sourceNode.next[sourceChar];
        targetNode = (targetNode == null) ? null : targetNode.next[targetChar];

        if (sourceNode == null || targetNode == null) {
          break;
        }

        int sourceWordId = sourceNode.wordId;
        int targetWordId = targetNode.wordId;

        if (sourceWordId != -1 && targetWordId != -1) {
          long conversionCost = minCostBetween[sourceWordId][targetWordId];
          if (conversionCost != INF) {
            dp[endIndex + 1] = Math.min(dp[endIndex + 1], dp[startIndex] + conversionCost);
          }
        }
      }
    }

    return dp[n] == INF ? -1 : dp[n];
  }

  static void main() {
    Solution sol = new Solution();

    String source = "abcd";
    String target = "acbe";
    String[] original = {"a", "b", "c", "c", "e", "d"};
    String[] changed = {"b", "c", "b", "e", "b", "e"};
    int[] cost = {2, 5, 5, 1, 2, 20};
    System.out.println(sol.minimumCost(source, target, original, changed, cost));
  }
}
