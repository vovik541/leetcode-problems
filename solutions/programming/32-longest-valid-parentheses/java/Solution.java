package dev.vkh.solutions;

class Solution {

  public static int longestValidParentheses(String s) {
    int[] stack = new int[s.length() + 1];
    int top = 0;
    stack[0] = -1;

    int maximumLength = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack[++top] = i;
      } else {
        top--;

        if (top < 0) {
          top = 0;
          stack[0] = i;
        } else {
          maximumLength = Math.max(maximumLength, i - stack[top]);
        }
      }
    }

    return maximumLength;
  }

  static void main() {
    System.out.println(longestValidParentheses("(()")); // 2
    System.out.println(longestValidParentheses(")()())")); // 4
    System.out.println(longestValidParentheses("")); // 0
    System.out.println(longestValidParentheses("()(()")); // 2
  }
}
