package dev.vkh.solutions;

public class Solution {

  public static int uniqueXorTriplets(int[] nums) {
    int n = nums.length;

    if (n <= 2) {
      return n;
    }

    int answer = 1;

    while (answer <= n) {
      answer <<= 1;
    }

    return answer;
  }

  static void main() {
    System.out.println(uniqueXorTriplets(new int[] {1, 2})); // Output: 2
    System.out.println(uniqueXorTriplets(new int[] {3, 1, 2})); // Output: 4
    System.out.println(uniqueXorTriplets(new int[] {1, 2, 3, 4, 5})); // Output: 8
  }
}
