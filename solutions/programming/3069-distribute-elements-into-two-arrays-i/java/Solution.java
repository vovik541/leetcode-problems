package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public static int[] resultArray(int[] nums) {
    List<Integer> arr1 = new ArrayList<>();
    List<Integer> arr2 = new ArrayList<>();

    arr1.add(nums[0]);
    arr2.add(nums[1]);

    for (int i = 2; i < nums.length; i++) {
      int lastArr1 = arr1.get(arr1.size() - 1);
      int lastArr2 = arr2.get(arr2.size() - 1);

      if (lastArr1 > lastArr2) {
        arr1.add(nums[i]);
      } else {
        arr2.add(nums[i]);
      }
    }

    int[] result = new int[nums.length];
    int index = 0;

    for (int num : arr1) {
      result[index] = num;
      index++;
    }

    for (int num : arr2) {
      result[index] = num;
      index++;
    }

    return result;
  }

  static void main() {
    System.out.println(Arrays.toString(resultArray(new int[] {2, 1, 3}))); // [2,3,1]
    System.out.println(Arrays.toString(resultArray(new int[] {5, 4, 3, 8}))); // [5,3,4,8]
  }
}
