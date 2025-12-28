package easy.countNegativeNumbersInSortedMatrix;

class Solution {

    public static void main(String[] args) {

        System.out.println(countNegatives((new int[][]{
                {4, 3, 2, -1},
                {3, 2, 1, -1},
                {1, 1, -1, -2},
                {-1, -1, -2, -3}
        })));
    }

    public static int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int total = 0;

        for (int row = 0; row < m; row++) {
            if (grid[row][n - 1] >= 0) continue;
            if (grid[row][0] < 0) {
                total += n;
                continue;
            }

            int left = 0, right = n - 1;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (grid[row][mid] < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            total += (n - left);
        }

        return total;
    }
}