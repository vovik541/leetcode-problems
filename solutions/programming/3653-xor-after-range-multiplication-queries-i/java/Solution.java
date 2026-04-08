package dev.vkh.solutions;

class Solution {
  private static final int MOD = 1_000_000_007;

  public int xorAfterQueries(int[] nums, int[][] queries) {
    for (int[] query : queries) {
      for (int idx = query[0]; idx <= query[1]; idx += query[2]) {
        nums[idx] = (int) ((1L * nums[idx] * query[3]) % MOD);
      }
    }

    int xor = 0;
    for (int value : nums) {
      xor ^= value;
    }

    return xor;
  }

  static void main() {
    System.out.println(
        new Solution()
            .xorAfterQueries(
                new int[] {2, 3, 1, 5, 4}, new int[][] {{1, 4, 2, 3}, {0, 2, 1, 2}})); // 31
  }
}
