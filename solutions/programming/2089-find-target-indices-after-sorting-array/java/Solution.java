package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {

  public static List<Integer> targetIndices(int[] nums, int target) {
    int lessCount = 0;
    int equalCount = 0;

    for (int number : nums) {
      if (number < target) {
        lessCount++;
      } else if (number == target) {
        equalCount++;
      }
    }

    List<Integer> result = new ArrayList<>();

    for (int i = lessCount; i < lessCount + equalCount; i++) {
      result.add(i);
    }

    return result;
  }

  static void main() {
    System.out.println(targetIndices(new int[] {1, 2, 5, 2, 3}, 2)); // [1, 2]
    System.out.println(targetIndices(new int[] {1, 2, 5, 2, 3}, 3)); // [3]
    System.out.println(targetIndices(new int[] {1, 2, 5, 2, 3}, 5)); // [4]
    System.out.println(targetIndices(new int[] {1, 2, 3}, 4)); // []
  }
}
