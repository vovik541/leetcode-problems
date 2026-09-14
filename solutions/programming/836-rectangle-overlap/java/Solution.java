package dev.vkh.solutions;

class Solution {
  public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
        && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
  }

  static void main() {
    System.out.println(isRectangleOverlap(new int[] {0, 0, 2, 2}, new int[] {1, 1, 3, 3})); // true
    System.out.println(isRectangleOverlap(new int[] {0, 0, 1, 1}, new int[] {1, 0, 2, 1})); // false
    System.out.println(isRectangleOverlap(new int[] {0, 0, 1, 1}, new int[] {2, 2, 3, 3})); // false
  }
}
