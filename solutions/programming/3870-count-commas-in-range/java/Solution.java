package dev.vkh.solutions;

class Solution {

  public static int countCommas(int n) {
    return n < 1000 ? 0 : n - 999;
  }

  static void main() {
    System.out.println(countCommas(1002)); // 3
    System.out.println(countCommas(998)); // 0
  }
}
