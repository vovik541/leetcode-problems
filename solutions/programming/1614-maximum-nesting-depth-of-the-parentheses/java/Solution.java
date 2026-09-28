package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static int maxDepth(String s) {
    int currentDepth = 0;
    int maximumDepth = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        currentDepth++;
        maximumDepth = Math.max(maximumDepth, currentDepth);
      } else if (current == ')') {
        currentDepth--;
      }
    }

    return maximumDepth;
  }

  static void main() {
    System.out.println(maxDepth("(1+(2*3)+((8)/4))+1")); // 3
    System.out.println(maxDepth("(1)+((2))+(((3)))")); // 3
    System.out.println(maxDepth("()(())((()()))")); // 3
  }
}
