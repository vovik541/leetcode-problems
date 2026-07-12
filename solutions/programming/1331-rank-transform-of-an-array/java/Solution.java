package dev.vkh.solutions;

import java.util.Arrays;
import java.util.HashMap;

class Solution {
  public static int[] arrayRankTransform(int[] arr) {
    int[] sorted = arr.clone();
    Arrays.sort(sorted);
    HashMap<Integer, Integer> unique = new HashMap<>();
    for (int i = 0; i < sorted.length; i++) {
      unique.putIfAbsent(sorted[i], unique.size() + 1);
    }

    int[] answer = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      answer[i] = unique.get(arr[i]);
    }

    return answer;
  }

  static void main() {
    System.out.println(
        Arrays.toString(arrayRankTransform(new int[] {40, 10, 20, 30}))); // [4,1,2,3]
    System.out.println(Arrays.toString(arrayRankTransform(new int[] {100, 100, 100}))); // [1,1,1]
  }
}
