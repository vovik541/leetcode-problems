package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {
  public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);

    arr[0] = 1;

    for (int i = 1; i < arr.length; i++) {
      arr[i] = Math.min(arr[i], arr[i - 1] + 1);
    }

    return arr[arr.length - 1];
  }

  void main() {
    int[] arr = {2, 2, 1, 2, 1};
    System.out.println(maximumElementAfterDecrementingAndRearranging(arr)); // Output: 2
  }
}
