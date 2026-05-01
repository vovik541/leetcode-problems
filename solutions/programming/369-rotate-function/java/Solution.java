package dev.vkh.solutions;

class Solution {
  public int maxRotateFunction(int[] A) {
    if (A == null || A.length == 0) return 0;
    int sum = 0, F0 = 0, max = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; i++) {
      sum += A[i];
      F0 += i * A[i];
    }
    int dp[] = new int[A.length];
    dp[0] = F0;
    max = dp[0];
    for (int i = 1; i < A.length; i++) {
      dp[i] = dp[i - 1] + sum - A.length * A[A.length - i];
      max = Math.max(max, dp[i]);
    }
    return max;
  }

  //  public int maxRotateFunction(int[] nums) {
  //    int maxFunctionValue = Integer.MIN_VALUE;
  //
  //    for (int k = 0; k < nums.length; k++) {
  //      int sum = 0;
  //      for (int i = 0; i < nums.length; i++) {
  //        sum += i * nums[i - k >= 0 ? i - k : nums.length + i - k];
  //      }
  //      maxFunctionValue = Math.max(maxFunctionValue, sum);
  //    }
  //
  //    return maxFunctionValue;
  //  }

  static void main() {
    System.out.println(new Solution().maxRotateFunction(new int[] {4, 3, 2, 6}));
  }
}
