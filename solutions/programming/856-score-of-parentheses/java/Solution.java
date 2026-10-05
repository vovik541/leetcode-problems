package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static int scoreOfParentheses(String s) {
    Stack<Integer> stack = new Stack<>();
    stack.push(0);

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.push(0);
      } else {
        int innerScore = stack.pop();

        int score = innerScore == 0 ? 1 : 2 * innerScore;

        stack.push(stack.pop() + score);
      }
    }

    return stack.peek();
  }

  static void main() {
    System.out.println(scoreOfParentheses("()")); // 1
    System.out.println(scoreOfParentheses("(())")); // 2
    System.out.println(scoreOfParentheses("()()")); // 2
    System.out.println(scoreOfParentheses("(()())")); // 4
    System.out.println(scoreOfParentheses("(()(()))")); // 6
  }
}
