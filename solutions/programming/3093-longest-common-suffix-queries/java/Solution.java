package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
    TrieNode root = new TrieNode();

    for (int wordIndex = 0; wordIndex < wordsContainer.length; wordIndex++) {
      insertReversedWord(root, wordsContainer[wordIndex], wordIndex, wordsContainer);
    }

    int[] answer = new int[wordsQuery.length];

    for (int queryIndex = 0; queryIndex < wordsQuery.length; queryIndex++) {
      answer[queryIndex] = findBestMatchingIndex(root, wordsQuery[queryIndex]);
    }

    return answer;
  }

  private void insertReversedWord(
      TrieNode root, String word, int wordIndex, String[] wordsContainer) {
    updateBestIndex(root, wordIndex, wordsContainer);

    TrieNode currentNode = root;

    for (int characterIndex = word.length() - 1; characterIndex >= 0; characterIndex--) {
      int childIndex = word.charAt(characterIndex) - 'a';

      if (currentNode.children[childIndex] == null) {
        currentNode.children[childIndex] = new TrieNode();
      }

      currentNode = currentNode.children[childIndex];
      updateBestIndex(currentNode, wordIndex, wordsContainer);
    }
  }

  private int findBestMatchingIndex(TrieNode root, String queryWord) {
    TrieNode currentNode = root;
    int bestIndex = currentNode.bestWordIndex;

    for (int characterIndex = queryWord.length() - 1; characterIndex >= 0; characterIndex--) {
      int childIndex = queryWord.charAt(characterIndex) - 'a';

      if (currentNode.children[childIndex] == null) {
        break;
      }

      currentNode = currentNode.children[childIndex];
      bestIndex = currentNode.bestWordIndex;
    }

    return bestIndex;
  }

  private void updateBestIndex(TrieNode node, int candidateWordIndex, String[] wordsContainer) {
    if (node.bestWordIndex == -1) {
      node.bestWordIndex = candidateWordIndex;
      return;
    }

    String currentBestWord = wordsContainer[node.bestWordIndex];
    String candidateWord = wordsContainer[candidateWordIndex];

    if (candidateWord.length() < currentBestWord.length()) {
      node.bestWordIndex = candidateWordIndex;
    } else if (candidateWord.length() == currentBestWord.length()
        && candidateWordIndex < node.bestWordIndex) {
      node.bestWordIndex = candidateWordIndex;
    }
  }

  private static class TrieNode {
    private final TrieNode[] children = new TrieNode[26];
    private int bestWordIndex = -1;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
        Arrays.toString(
            solution.stringIndices(
                new String[] {"abcdefgh", "poiuygh", "ghghgh"},
                new String[] {"gh", "acbfgh", "acbfegh"}))); // [2, 0, 2]
  }
}
