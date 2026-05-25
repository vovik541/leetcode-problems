package dev.vkh.solutions;

class Solution {

  public boolean canReach(String s, int minJump, int maxJump) {
    int stringLength = s.length();

    if (s.charAt(stringLength - 1) != '0') {
      return false;
    }

    boolean[] reachable = new boolean[stringLength];
    reachable[0] = true;

    int reachableWindowCount = 0;

    for (int currentIndex = 1; currentIndex < stringLength; currentIndex++) {
      int indexEnteringWindow = currentIndex - minJump;
      if (indexEnteringWindow >= 0 && reachable[indexEnteringWindow]) {
        reachableWindowCount++;
      }

      int indexLeavingWindow = currentIndex - maxJump - 1;
      if (indexLeavingWindow >= 0 && reachable[indexLeavingWindow]) {
        reachableWindowCount--;
      }

      reachable[currentIndex] = s.charAt(currentIndex) == '0' && reachableWindowCount > 0;
    }

    return reachable[stringLength - 1];
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.canReach("011010", 2, 3)); // true
    System.out.println(solution.canReach("01101110", 2, 3)); // false
  }
}
