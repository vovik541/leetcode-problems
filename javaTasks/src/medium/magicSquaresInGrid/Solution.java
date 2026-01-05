package medium.magicSquaresInGrid;

class Solution {

    public static void main(String[] args) {
        int[][] grid = {
                {4, 3, 8, 4},
                {9, 5, 1, 9},
                {2, 7, 6, 2}
        };

        System.out.println(new Solution().numMagicSquaresInside(grid));
    }

    public int numMagicSquaresInside(int[][] grid) {
        int rowCount = grid.length;
        int columnCount = grid[0].length;

        if (rowCount < 3 || columnCount < 3) {
            return 0;
        }

        int magicSquareCount = 0;

        for (int topRow = 0; topRow <= rowCount - 3; topRow++) {
            for (int leftColumn = 0; leftColumn <= columnCount - 3; leftColumn++) {
                if (isMagicSquare3x3(grid, topRow, leftColumn)) {
                    magicSquareCount++;
                }
            }
        }

        return magicSquareCount;
    }

    private boolean isMagicSquare3x3(int[][] grid, int topRow, int leftColumn) {
        return hasCenterFive(grid, topRow, leftColumn)
                && containsAllNumbersFromOneToNine(grid, topRow, leftColumn)
                && hasEqualRowSums(grid, topRow, leftColumn)
                && hasEqualColumnSums(grid, topRow, leftColumn)
                && hasEqualDiagonalSums(grid, topRow, leftColumn);
    }

    private boolean hasCenterFive(int[][] grid, int topRow, int leftColumn) {
        return grid[topRow + 1][leftColumn + 1] == 5;
    }

    private boolean containsAllNumbersFromOneToNine(int[][] grid, int topRow, int leftColumn) {
        boolean[] usedNumbers = new boolean[10];

        for (int row = topRow; row < topRow + 3; row++) {
            for (int col = leftColumn; col < leftColumn + 3; col++) {
                int value = grid[row][col];

                if (value < 1 || value > 9 || usedNumbers[value]) {
                    return false;
                }

                usedNumbers[value] = true;
            }
        }

        return true;
    }

    private boolean hasEqualRowSums(int[][] grid, int topRow, int leftColumn) {
        int targetSum = calculateRowSum(grid, topRow, leftColumn);

        for (int rowOffset = 0; rowOffset < 3; rowOffset++) {
            if (calculateRowSum(grid, topRow + rowOffset, leftColumn) != targetSum) {
                return false;
            }
        }

        return true;
    }

    private boolean hasEqualColumnSums(int[][] grid, int topRow, int leftColumn) {
        int targetSum = calculateColumnSum(grid, topRow, leftColumn);

        for (int columnOffset = 0; columnOffset < 3; columnOffset++) {
            if (calculateColumnSum(grid, topRow, leftColumn + columnOffset) != targetSum) {
                return false;
            }
        }

        return true;
    }

    private boolean hasEqualDiagonalSums(int[][] grid, int topRow, int leftColumn) {
        int targetSum = calculateMainDiagonalSum(grid, topRow, leftColumn);

        return calculateMainDiagonalSum(grid, topRow, leftColumn) == targetSum
                && calculateAntiDiagonalSum(grid, topRow, leftColumn) == targetSum;
    }

    private int calculateRowSum(int[][] grid, int row, int leftColumn) {
        return grid[row][leftColumn]
                + grid[row][leftColumn + 1]
                + grid[row][leftColumn + 2];
    }

    private int calculateColumnSum(int[][] grid, int topRow, int column) {
        return grid[topRow][column]
                + grid[topRow + 1][column]
                + grid[topRow + 2][column];
    }

    private int calculateMainDiagonalSum(int[][] grid, int topRow, int leftColumn) {
        return grid[topRow][leftColumn]
                + grid[topRow + 1][leftColumn + 1]
                + grid[topRow + 2][leftColumn + 2];
    }

    private int calculateAntiDiagonalSum(int[][] grid, int topRow, int leftColumn) {
        return grid[topRow][leftColumn + 2]
                + grid[topRow + 1][leftColumn + 1]
                + grid[topRow + 2][leftColumn];
    }
}