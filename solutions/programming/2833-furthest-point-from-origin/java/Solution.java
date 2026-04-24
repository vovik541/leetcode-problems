package dev.vkh.solutions;

class Solution {
  public int furthestDistanceFromOrigin(String moves) {
    int rightDirection = 0;
    int leftDirection = 0;
    int anyDirection = 0;

    for (char move : moves.toCharArray()) {
      if (move == 'R') {
        rightDirection++;
      } else if (move == 'L') {
        leftDirection++;
      } else {
        anyDirection++;
      }
    }

    return Math.abs(rightDirection - leftDirection) + anyDirection;
  }

  static void main() {
    System.out.println(new Solution().furthestDistanceFromOrigin("L_RL__R"));
  }
}
