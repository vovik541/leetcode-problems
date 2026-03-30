package dev.vkh.solutions;

class Solution {

  static void main() {
    Solution solution = new Solution();

    int[][] lcp = {
      {4, 0, 2, 0},
      {0, 3, 0, 1},
      {2, 0, 2, 0},
      {0, 1, 0, 1}
    };
    System.out.println(solution.findTheString(lcp)); // abab
  }

  public String findTheString(int[][] lcp) {
    int stringLength = lcp.length;

    for (int rowIndex = 0; rowIndex < stringLength; rowIndex++) {
      if (lcp[rowIndex][rowIndex] != stringLength - rowIndex) {
        return "";
      }
    }

    DisjointSetUnion disjointSetUnion = new DisjointSetUnion(stringLength);

    for (int firstIndex = 0; firstIndex < stringLength; firstIndex++) {
      for (int secondIndex = firstIndex + 1; secondIndex < stringLength; secondIndex++) {
        if (lcp[firstIndex][secondIndex] > 0) {
          disjointSetUnion.union(firstIndex, secondIndex);
        }
      }
    }

    char[] resultCharacters = new char[stringLength];
    int nextCharacterOffset = 0;

    for (int index = 0; index < stringLength; index++) {
      if (resultCharacters[index] != '\0') {
        continue;
      }

      int currentRoot = disjointSetUnion.find(index);
      char assignedCharacter = (char) ('a' + nextCharacterOffset);

      if (nextCharacterOffset >= 26) {
        return "";
      }

      for (int otherIndex = index; otherIndex < stringLength; otherIndex++) {
        if (disjointSetUnion.find(otherIndex) == currentRoot) {
          resultCharacters[otherIndex] = assignedCharacter;
        }
      }

      nextCharacterOffset++;
    }

    int[][] computedLcp = new int[stringLength + 1][stringLength + 1];

    for (int firstIndex = stringLength - 1; firstIndex >= 0; firstIndex--) {
      for (int secondIndex = stringLength - 1; secondIndex >= 0; secondIndex--) {
        if (resultCharacters[firstIndex] == resultCharacters[secondIndex]) {
          computedLcp[firstIndex][secondIndex] = computedLcp[firstIndex + 1][secondIndex + 1] + 1;
        }
      }
    }

    for (int firstIndex = 0; firstIndex < stringLength; firstIndex++) {
      for (int secondIndex = 0; secondIndex < stringLength; secondIndex++) {
        if (computedLcp[firstIndex][secondIndex] != lcp[firstIndex][secondIndex]) {
          return "";
        }
      }
    }

    return new String(resultCharacters);
  }

  private static class DisjointSetUnion {
    private final int[] parentByIndex;
    private final int[] sizeByRoot;

    private DisjointSetUnion(int size) {
      this.parentByIndex = new int[size];
      this.sizeByRoot = new int[size];

      for (int index = 0; index < size; index++) {
        parentByIndex[index] = index;
        sizeByRoot[index] = 1;
      }
    }

    private int find(int index) {
      if (parentByIndex[index] != index) {
        parentByIndex[index] = find(parentByIndex[index]);
      }
      return parentByIndex[index];
    }

    private void union(int firstIndex, int secondIndex) {
      int firstRoot = find(firstIndex);
      int secondRoot = find(secondIndex);

      if (firstRoot == secondRoot) {
        return;
      }

      if (sizeByRoot[firstRoot] < sizeByRoot[secondRoot]) {
        int temporaryRoot = firstRoot;
        firstRoot = secondRoot;
        secondRoot = temporaryRoot;
      }

      parentByIndex[secondRoot] = firstRoot;
      sizeByRoot[firstRoot] += sizeByRoot[secondRoot];
    }
  }
}
