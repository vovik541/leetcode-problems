package hard.numberOfWaysToPaintN3Grid;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().numOfWays(5000));
    }

    private static final int MOD = 1_000_000_007;

    public int numOfWays(int n) {
        long twoColorRowCount = 6;
        long threeColorRowCount = 6;

        for (int rowIndex = 2; rowIndex <= n; rowIndex++) {
            long nextTwoColorRowCount =
                    (twoColorRowCount * 3 + threeColorRowCount * 2) % MOD;

            long nextThreeColorRowCount =
                    (twoColorRowCount * 2 + threeColorRowCount * 2) % MOD;

            twoColorRowCount = nextTwoColorRowCount;
            threeColorRowCount = nextThreeColorRowCount;
        }

        return (int) ((twoColorRowCount + threeColorRowCount) % MOD);
    }
}