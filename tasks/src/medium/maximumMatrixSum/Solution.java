package medium.maximumMatrixSum;

class Solution {

    public static void main(String[] args) {
        int[][] matrix = {
                {4, -3, 8, 4},
                {9, 5, 1, 9},
                {2, -7, -6, 2},
                {2, 7, 12, 1}
        };

        System.out.println(new Solution().maxMatrixSum(matrix));
    }

    public long maxMatrixSum(int[][] matrix) {
        long sumOfAbsoluteValues = 0L;
        int negativeCount = 0;
        int minimumAbsoluteValue = Integer.MAX_VALUE;

        for (int[] row : matrix) {
            for (int value : row) {
                if (value < 0) negativeCount++;

                int absoluteValue = Math.abs(value);
                sumOfAbsoluteValues += absoluteValue;
                minimumAbsoluteValue = Math.min(minimumAbsoluteValue, absoluteValue);
            }
        }

        if (negativeCount % 2 == 0) {
            return sumOfAbsoluteValues;
        }

        return sumOfAbsoluteValues - 2L * minimumAbsoluteValue;
    }
}