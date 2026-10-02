package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    backtrack(n, 0, 0, new StringBuilder(), result);
    return result;
  }

  private static void backtrack(
      int n, int openCount, int closeCount, StringBuilder current, List<String> result) {

    if (current.length() == n * 2) {
      result.add(current.toString());
      return;
    }

    // We can add '(' while we still have unused opening brackets.
    if (openCount < n) {
      current.append('(');

      backtrack(n, openCount + 1, closeCount, current, result);

      current.deleteCharAt(current.length() - 1);
    }

    // We can add ')' only if there is an unmatched '('.
    if (closeCount < openCount) {
      current.append(')');

      backtrack(n, openCount, closeCount + 1, current, result);

      current.deleteCharAt(current.length() - 1);
    }
  }

  static void main() {
    System.out.println(generateParenthesis(3)); // [((())), (()()), (())(), ()(()), ()()()]
    System.out.println(generateParenthesis(1)); // [()]
  }
}
