package dev.vkh.solutions;

public class Solution {

  private static class Node {
    char leftChar;
    char rightChar;

    int prefixLength;
    int suffixLength;
    int maxLength;
    int length;

    Node() {}

    Node(char character) {
      this.leftChar = character;
      this.rightChar = character;
      this.prefixLength = 1;
      this.suffixLength = 1;
      this.maxLength = 1;
      this.length = 1;
    }
  }

  private static Node[] segmentTree;
  private static char[] characters;

  public static int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
    int n = s.length();

    characters = s.toCharArray();
    segmentTree = new Node[4 * n];

    build(1, 0, n - 1);

    int queryCount = queryCharacters.length();
    int[] answer = new int[queryCount];

    for (int query = 0; query < queryCount; query++) {
      int index = queryIndices[query];
      char newCharacter = queryCharacters.charAt(query);

      update(1, 0, n - 1, index, newCharacter);

      answer[query] = segmentTree[1].maxLength;
    }

    return answer;
  }

  private static void build(int treeIndex, int left, int right) {
    if (left == right) {
      segmentTree[treeIndex] = new Node(characters[left]);
      return;
    }

    int middle = left + (right - left) / 2;

    build(treeIndex * 2, left, middle);
    build(treeIndex * 2 + 1, middle + 1, right);

    segmentTree[treeIndex] = merge(segmentTree[treeIndex * 2], segmentTree[treeIndex * 2 + 1]);
  }

  private static void update(
      int treeIndex, int left, int right, int targetIndex, char newCharacter) {
    if (left == right) {
      characters[targetIndex] = newCharacter;
      segmentTree[treeIndex] = new Node(newCharacter);
      return;
    }

    int middle = left + (right - left) / 2;

    if (targetIndex <= middle) {
      update(treeIndex * 2, left, middle, targetIndex, newCharacter);
    } else {
      update(treeIndex * 2 + 1, middle + 1, right, targetIndex, newCharacter);
    }

    segmentTree[treeIndex] = merge(segmentTree[treeIndex * 2], segmentTree[treeIndex * 2 + 1]);
  }

  private static Node merge(Node leftNode, Node rightNode) {
    Node mergedNode = new Node();

    mergedNode.length = leftNode.length + rightNode.length;

    mergedNode.leftChar = leftNode.leftChar;
    mergedNode.rightChar = rightNode.rightChar;

    mergedNode.prefixLength = leftNode.prefixLength;
    mergedNode.suffixLength = rightNode.suffixLength;

    mergedNode.maxLength = Math.max(leftNode.maxLength, rightNode.maxLength);

    if (leftNode.rightChar == rightNode.leftChar) {
      int combinedMiddleLength = leftNode.suffixLength + rightNode.prefixLength;

      mergedNode.maxLength = Math.max(mergedNode.maxLength, combinedMiddleLength);

      if (leftNode.prefixLength == leftNode.length) {
        mergedNode.prefixLength = leftNode.length + rightNode.prefixLength;
      }

      if (rightNode.suffixLength == rightNode.length) {
        mergedNode.suffixLength = rightNode.length + leftNode.suffixLength;
      }
    }

    return mergedNode;
  }

  static void main() {

    String s1 = "babacc";
    String queryCharacters1 = "bcb";
    int[] queryIndices1 = {1, 3, 3};

    int[] result1 = longestRepeating(s1, queryCharacters1, queryIndices1);
    printArray(result1); // [3, 3, 4]

    String s2 = "abyzz";
    String queryCharacters2 = "aa";
    int[] queryIndices2 = {2, 1};

    int[] result2 = longestRepeating(s2, queryCharacters2, queryIndices2);
    printArray(result2); // [2, 3]
  }

  private static void printArray(int[] array) {
    System.out.print("[");
    for (int index = 0; index < array.length; index++) {
      if (index > 0) {
        System.out.print(", ");
      }
      System.out.print(array[index]);
    }
    System.out.println("]");
  }
}
