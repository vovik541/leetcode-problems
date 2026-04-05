package dev.vkh.solutions;

class Solution {
  public boolean judgeCircle(String moves) {
    int horizontal = 0;
    int vertical = 0;

    for (char move : moves.toCharArray()) {
      if ('U' == move) {
        vertical++;
        continue;
      }
      if ('D' == move) {
        vertical--;
        continue;
      }
      if ('R' == move) {
        horizontal++;
        continue;
      }
      if ('L' == move) {
        horizontal--;
      }
    }

    return horizontal == 0 && vertical == 0;
  }

  static void main() {
    System.out.println(new Solution().judgeCircle("RR"));
  }
}
