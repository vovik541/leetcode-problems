package dev.vkh.solutions;

class Solution {

  public static int minAddToMakeValid(String s) {
    int openCount = 0;
    int additions = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        openCount++;
      } else if (openCount > 0) {
        openCount--;
      } else {
        additions++;
      }
    }

    return additions + openCount;
  }

  static void main() {
    System.out.println(minAddToMakeValid("())")); // 1
    System.out.println(minAddToMakeValid("(((")); // 3
    System.out.println(minAddToMakeValid("()")); // 0
    System.out.println(minAddToMakeValid("()))((")); // 4
    System.out.println(minAddToMakeValid(")))")); // 3
  }
}
