package dev.vkh.solutions;

class Solution {

  public static String removeOuterParentheses(String s) {
    StringBuilder result = new StringBuilder();
    int depth = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        if (depth > 0) {
          result.append(current);
        }

        depth++;
      } else {
        depth--;

        if (depth > 0) {
          result.append(current);
        }
      }
    }

    return result.toString();
  }

  static void main() {
    System.out.println(removeOuterParentheses("(()())(())"));// ()()()
    System.out.println(removeOuterParentheses("(()())(())(()(()))"));// ()()()()(())
    System.out.println(removeOuterParentheses("()()"));// ""
    System.out.println(removeOuterParentheses("((()))"));// (())
  }
}
