package dev.vkh.solutions.hard.lastDayWhereYouCanStillCross;

public class Solution {

  private static final int[] ROW_OFFSETS = {1, -1, 0, 0};
  private static final int[] COLUMN_OFFSETS = {0, 0, 1, -1};

  private static class DisjointSetUnion {
    private final int[] parentByNode;
    private final int[] sizeByRoot;

    DisjointSetUnion(int totalNodes) {
      parentByNode = new int[totalNodes];
      sizeByRoot = new int[totalNodes];
      for (int nodeIndex = 0; nodeIndex < totalNodes; nodeIndex++) {
        parentByNode[nodeIndex] = nodeIndex;
        sizeByRoot[nodeIndex] = 1;
      }
    }

    int findRoot(int nodeIndex) {
      while (parentByNode[nodeIndex] != nodeIndex) {
        parentByNode[nodeIndex] = parentByNode[parentByNode[nodeIndex]];
        nodeIndex = parentByNode[nodeIndex];
      }
      return nodeIndex;
    }

    void union(int firstNode, int secondNode) {
      int firstRoot = findRoot(firstNode);
      int secondRoot = findRoot(secondNode);
      if (firstRoot == secondRoot) return;

      if (sizeByRoot[firstRoot] < sizeByRoot[secondRoot]) {
        int temp = firstRoot;
        firstRoot = secondRoot;
        secondRoot = temp;
      }

      parentByNode[secondRoot] = firstRoot;
      sizeByRoot[firstRoot] += sizeByRoot[secondRoot];
    }

    boolean connected(int firstNode, int secondNode) {
      return findRoot(firstNode) == findRoot(secondNode);
    }
  }

  public int latestDayToCross(int row, int col, int[][] cells) {
    int totalCells = row * col;
    int virtualTopNode = totalCells;
    int virtualBottomNode = totalCells + 1;

    DisjointSetUnion disjointSetUnion = new DisjointSetUnion(totalCells + 2);
    boolean[] isLandByCellIndex = new boolean[totalCells];

    for (int dayIndex = cells.length - 1; dayIndex >= 0; dayIndex--) {
      int zeroBasedRow = cells[dayIndex][0] - 1;
      int zeroBasedColumn = cells[dayIndex][1] - 1;
      int currentCellIndex = zeroBasedRow * col + zeroBasedColumn;

      isLandByCellIndex[currentCellIndex] = true;

      if (zeroBasedRow == 0) disjointSetUnion.union(currentCellIndex, virtualTopNode);
      if (zeroBasedRow == row - 1) disjointSetUnion.union(currentCellIndex, virtualBottomNode);

      for (int directionIndex = 0; directionIndex < 4; directionIndex++) {
        int neighborRow = zeroBasedRow + ROW_OFFSETS[directionIndex];
        int neighborColumn = zeroBasedColumn + COLUMN_OFFSETS[directionIndex];

        if (neighborRow < 0 || neighborRow >= row || neighborColumn < 0 || neighborColumn >= col) {
          continue;
        }

        int neighborCellIndex = neighborRow * col + neighborColumn;
        if (isLandByCellIndex[neighborCellIndex]) {
          disjointSetUnion.union(currentCellIndex, neighborCellIndex);
        }
      }

      if (disjointSetUnion.connected(virtualTopNode, virtualBottomNode)) {
        return dayIndex;
      }
    }

    return 0;
  }

  static void main(String[] args) {
    Solution solution = new Solution();

    int row1 = 2, col1 = 2;
    int[][] cells1 = {{1, 1}, {2, 1}, {1, 2}, {2, 2}};
    System.out.println("Expected 2, got: " + solution.latestDayToCross(row1, col1, cells1));

    int row2 = 2, col2 = 2;
    int[][] cells2 = {{1, 1}, {1, 2}, {2, 1}, {2, 2}};
    System.out.println("Expected 1, got: " + solution.latestDayToCross(row2, col2, cells2));

    int row3 = 3, col3 = 3;
    int[][] cells3 = {{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}};
    System.out.println("Expected 3, got: " + solution.latestDayToCross(row3, col3, cells3));
  }
}
