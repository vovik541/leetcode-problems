package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  private static final long MOD = 1_000_000_007L;

  public static int numOfWays(int[] nums) {
    int length = nums.length;

    long[][] combinations = new long[length + 1][length + 1];

    for (int i = 0; i <= length; i++) {
      combinations[i][0] = 1;
      combinations[i][i] = 1;

      for (int j = 1; j < i; j++) {
        combinations[i][j] = (combinations[i - 1][j - 1] + combinations[i - 1][j]) % MOD;
      }
    }

    List<Integer> values = new ArrayList<>();

    for (int number : nums) {
      values.add(number);
    }

    long totalWays = countWays(values, combinations);

    return (int) ((totalWays - 1 + MOD) % MOD);
  }

  private static long countWays(List<Integer> values, long[][] combinations) {

    if (values.size() <= 2) {
      return 1;
    }

    int root = values.get(0);

    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    for (int i = 1; i < values.size(); i++) {
      int value = values.get(i);

      if (value < root) {
        left.add(value);
      } else {
        right.add(value);
      }
    }

    long leftWays = countWays(left, combinations);
    long rightWays = countWays(right, combinations);

    long arrangements = combinations[left.size() + right.size()][left.size()];

    return arrangements * leftWays % MOD * rightWays % MOD;
  }

  static void main() {
    System.out.println(numOfWays(new int[] {2, 1, 3})); // 1
    System.out.println(numOfWays(new int[] {3, 4, 5, 1, 2})); // 5
    System.out.println(numOfWays(new int[] {1, 2, 3})); // 0
    System.out.println(numOfWays(new int[] {3, 1, 2, 5, 4})); // 5
    System.out.println(numOfWays(new int[] {1})); // 0
  }
}
