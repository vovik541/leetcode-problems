package dev.vkh.solutions.medium.pyramidTransitionMatrix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Bitmask + DFS solution
public class SolutionBitMask {

  private int[][] transitionMasks = new int[7][7];

  private Set<String> failedRows = new HashSet<>();

  static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.pyramidTransition("BCD", List.of("BCC", "CDE", "CEA", "FFF")));
  }

  public boolean pyramidTransition(String bottomRow, List<String> allowedPatterns) {
    for (String pattern : allowedPatterns) {
      int leftBlock = pattern.charAt(0) - 'A';
      int rightBlock = pattern.charAt(1) - 'A';
      int topBlock = pattern.charAt(2) - 'A';

      transitionMasks[leftBlock][rightBlock] |= (1 << topBlock);
    }

    return canBuildPyramid(bottomRow);
  }

  private boolean canBuildPyramid(String currentRow) {
    if (currentRow.length() == 1) {
      return true;
    }

    if (failedRows.contains(currentRow)) {
      return false;
    }

    char[] blocks = currentRow.toCharArray();
    int rowLength = blocks.length;

    int[] possibleTopMasks = new int[rowLength - 1];

    for (int i = 0; i < rowLength - 1; i++) {
      int left = blocks[i] - 'A';
      int right = blocks[i + 1] - 'A';

      int possibleTops = transitionMasks[left][right];
      if (possibleTops == 0) {
        failedRows.add(currentRow);
        return false;
      }

      possibleTopMasks[i] = possibleTops;
    }

    if (buildNextRow(possibleTopMasks, 0, new char[rowLength - 1])) {
      return true;
    }

    failedRows.add(currentRow);
    return false;
  }

  private boolean buildNextRow(int[] possibleTopMasks, int position, char[] nextRow) {

    if (position == possibleTopMasks.length) {
      return canBuildPyramid(new String(nextRow));
    }

    int mask = possibleTopMasks[position];

    while (mask != 0) {
      int lowestBit = mask & -mask;
      int blockIndex = Integer.numberOfTrailingZeros(lowestBit);

      nextRow[position] = (char) ('A' + blockIndex);

      if (buildNextRow(possibleTopMasks, position + 1, nextRow)) {
        return true;
      }

      mask -= lowestBit;
    }

    return false;
  }
}
