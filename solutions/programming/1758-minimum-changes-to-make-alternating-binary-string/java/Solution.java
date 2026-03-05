package dev.vkh.solutions;

class Solution {
  public int minOperations(String s) {
    int firstZeroChanges = 0;
    int firstOncChanges = 0;

    for (int i = 0; i < s.length(); i++) {
      if (i % 2 == 0) {
        if (s.charAt(i) == '0') {
          firstOncChanges++;
        } else {
          firstZeroChanges++;
        }
      } else {
        if (s.charAt(i) == '0') {
          firstZeroChanges++;
        } else {
          firstOncChanges++;
        }
      }
    }

    return Math.min(firstZeroChanges, firstOncChanges);
  }

  static void main() {
    System.out.println(new Solution().minOperations("0101"));
  }
}
