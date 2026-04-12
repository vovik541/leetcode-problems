package dev.vkh.solutions;

import java.util.Arrays;

class Solution {
  private static final int UNUSED_FINGER = 26;
  private static final int ALPHABET_SIZE = 26;
  private static final int INF = 1_000_000_000;

  public int minimumDistance(String word) {
    int wordLength = word.length();
    int[][] dynamicProgramming = new int[wordLength][ALPHABET_SIZE + 1];

    for (int[] row : dynamicProgramming) {
      Arrays.fill(row, INF);
    }

    int firstCharacter = word.charAt(0) - 'A';
    dynamicProgramming[0][UNUSED_FINGER] = 0;

    for (int index = 0; index < wordLength - 1; index++) {
      int currentCharacter = word.charAt(index) - 'A';
      int nextCharacter = word.charAt(index + 1) - 'A';

      for (int otherFingerPosition = 0;
          otherFingerPosition <= ALPHABET_SIZE;
          otherFingerPosition++) {
        int currentCost = dynamicProgramming[index][otherFingerPosition];
        if (currentCost == INF) {
          continue;
        }

        int moveLastTypedFingerCost = getDistance(currentCharacter, nextCharacter);
        dynamicProgramming[index + 1][otherFingerPosition] =
            Math.min(
                dynamicProgramming[index + 1][otherFingerPosition],
                currentCost + moveLastTypedFingerCost);

        int moveOtherFingerCost = getDistance(otherFingerPosition, nextCharacter);
        dynamicProgramming[index + 1][currentCharacter] =
            Math.min(
                dynamicProgramming[index + 1][currentCharacter], currentCost + moveOtherFingerCost);
      }
    }

    int answer = INF;
    for (int otherFingerPosition = 0; otherFingerPosition <= ALPHABET_SIZE; otherFingerPosition++) {
      answer = Math.min(answer, dynamicProgramming[wordLength - 1][otherFingerPosition]);
    }

    return answer;
  }

  private int getDistance(int fromCharacter, int toCharacter) {
    if (fromCharacter == UNUSED_FINGER) {
      return 0;
    }

    int fromRow = fromCharacter / 6;
    int fromColumn = fromCharacter % 6;
    int toRow = toCharacter / 6;
    int toColumn = toCharacter % 6;

    return Math.abs(fromRow - toRow) + Math.abs(fromColumn - toColumn);
  }

  static void main() {
    System.out.println(new Solution().minimumDistance("CAKE"));
  }
}
