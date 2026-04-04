package dev.vkh.solutions;

import java.util.*;

class Solution {
  public String decodeCiphertext(String encodedText, int rows) {
    char[][] transpositionMatrix = new char[rows][encodedText.length() / rows];

    for (int currentRow = 0; currentRow < rows; currentRow++) {
      for (int currentCol = 0; currentCol < transpositionMatrix[0].length; currentCol++) {
        transpositionMatrix[currentRow][currentCol] =
            encodedText.charAt(currentRow * transpositionMatrix[0].length + currentCol);
      }
    }
    StringBuilder decodedTextBuilder = new StringBuilder();

    nextIndex:
    for (int firstRowColIndex = 0;
        firstRowColIndex < transpositionMatrix[0].length;
        firstRowColIndex++) {
      for (int j = 0; j < transpositionMatrix.length; j++) {
        if (firstRowColIndex + j == transpositionMatrix[0].length) continue nextIndex;
        decodedTextBuilder.append(transpositionMatrix[j][firstRowColIndex + j]);
      }
    }

    return decodedTextBuilder.toString().stripTrailing();
  }

  static void main() {
    System.out.println(new Solution().decodeCiphertext("iveo    eed   l te   olc", 4)); // "i love leetcode"
    System.out.println(new Solution().decodeCiphertext(" b  ac", 2)); // " abc"
  }
}
