package dev.vkh.solutions;

public class Solution {

  public static boolean stoneGameIX(int[] stones) {
    int[] remainderCount = new int[3];

    for (int stone : stones) {
      remainderCount[stone % 3]++;
    }

    int countZero = remainderCount[0];
    int countOne = remainderCount[1];
    int countTwo = remainderCount[2];

    if (countZero % 2 == 0) {
      return countOne > 0 && countTwo > 0;
    }

    return Math.abs(countOne - countTwo) > 2;
  }

  static void main() {
    System.out.println(stoneGameIX(new int[] {2, 1})); // true
    System.out.println(stoneGameIX(new int[] {2})); // false
    System.out.println(stoneGameIX(new int[] {5, 1, 2, 4, 3})); // false
    System.out.println(stoneGameIX(new int[] {1, 1, 1, 2})); // true
  }
}
