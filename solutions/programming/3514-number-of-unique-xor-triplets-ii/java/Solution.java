package dev.vkh.solutions;

public class Solution {

  public static int uniqueXorTriplets(int[] nums) {
    boolean[] exists = new boolean[2048];

    for (int num : nums) {
      exists[num] = true;
    }

    boolean[] pairXorExists = new boolean[2048];

    for (int firstNumber = 1; firstNumber < 2048; firstNumber++) {
      if (!exists[firstNumber]) {
        continue;
      }

      for (int secondNumber = 1; secondNumber < 2048; secondNumber++) {
        if (!exists[secondNumber]) {
          continue;
        }

        pairXorExists[firstNumber ^ secondNumber] = true;
      }
    }

    boolean[] tripletXorExists = new boolean[2048];

    for (int pairXor = 0; pairXor < 2048; pairXor++) {
      if (!pairXorExists[pairXor]) {
        continue;
      }

      for (int thirdNumber = 1; thirdNumber < 2048; thirdNumber++) {
        if (!exists[thirdNumber]) {
          continue;
        }

        tripletXorExists[pairXor ^ thirdNumber] = true;
      }
    }

    int uniqueCount = 0;

    for (boolean isPossible : tripletXorExists) {
      if (isPossible) {
        uniqueCount++;
      }
    }

    return uniqueCount;
  }

  static void main() {
    System.out.println(uniqueXorTriplets(new int[] {1, 3})); // Output: 2
    System.out.println(uniqueXorTriplets(new int[] {6, 7, 8, 9})); // Output: 4
    System.out.println(uniqueXorTriplets(new int[] {1, 2, 3})); // Output: 4
  }
}
