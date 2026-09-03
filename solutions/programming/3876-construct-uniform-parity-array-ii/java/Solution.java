package dev.vkh.solutions;

class Solution {

  public static boolean uniformArray(int[] nums1) {
    int minimumValue = Integer.MAX_VALUE;
    boolean hasOddNumber = false;

    for (int number : nums1) {
      minimumValue = Math.min(minimumValue, number);

      if ((number & 1) == 1) {
        hasOddNumber = true;
      }
    }

    return !hasOddNumber || (minimumValue & 1) == 1;
  }

  static void main() {
    System.out.println(uniformArray(new int[] {1, 4, 7})); // true
    System.out.println(uniformArray(new int[] {2, 3})); // false
    System.out.println(uniformArray(new int[] {4, 6})); // true
    System.out.println(uniformArray(new int[] {2, 5, 8})); // false
    System.out.println(uniformArray(new int[] {3, 4, 8})); // true
  }
}
