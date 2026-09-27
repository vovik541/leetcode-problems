package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static String reverseParentheses(String s) {
    Deque<StringBuilder> stack = new ArrayDeque<>();
    StringBuilder current = new StringBuilder();

    for (char character : s.toCharArray()) {
      if (character == '(') {
        stack.push(current);
        current = new StringBuilder();
      } else if (character == ')') {
        current.reverse();

        StringBuilder previous = stack.pop();
        previous.append(current);
        current = previous;
      } else {
        current.append(character);
      }
    }

    return current.toString();
  }

  static void main() {
    System.out.println(reverseParentheses("(abcd)"));// dcba
    System.out.println(reverseParentheses("(u(love)i"));// iloveu
    System.out.println(reverseParentheses("(ed(et(oc))el)"));// leetcode
  }
}
