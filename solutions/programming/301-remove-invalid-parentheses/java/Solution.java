package dev.vkh.solutions;

import java.util.*;

class Solution {

  public static List<String> removeInvalidParentheses(String s) {
    int leftRemovals = 0;
    int rightRemovals = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        leftRemovals++;
      } else if (current == ')') {
        if (leftRemovals > 0) {
          leftRemovals--;
        } else {
          rightRemovals++;
        }
      }
    }

    Set<String> result = new HashSet<>();

    backtrack(s, 0, 0, leftRemovals, rightRemovals, new StringBuilder(), result);

    return new ArrayList<>(result);
  }

  private static void backtrack(
      String s,
      int index,
      int openCount,
      int leftRemovals,
      int rightRemovals,
      StringBuilder current,
      Set<String> result) {

    if (index == s.length()) {
      if (openCount == 0 && leftRemovals == 0 && rightRemovals == 0) {
        result.add(current.toString());
      }

      return;
    }

    char character = s.charAt(index);

    if (character == '(') {
      if (leftRemovals > 0) {
        backtrack(s, index + 1, openCount, leftRemovals - 1, rightRemovals, current, result);
      }

      current.append(character);

      backtrack(s, index + 1, openCount + 1, leftRemovals, rightRemovals, current, result);

      current.deleteCharAt(current.length() - 1);

    } else if (character == ')') {
      if (rightRemovals > 0) {
        backtrack(s, index + 1, openCount, leftRemovals, rightRemovals - 1, current, result);
      }

      if (openCount > 0) {
        current.append(character);

        backtrack(s, index + 1, openCount - 1, leftRemovals, rightRemovals, current, result);

        current.deleteCharAt(current.length() - 1);
      }

    } else {
      current.append(character);

      backtrack(s, index + 1, openCount, leftRemovals, rightRemovals, current, result);

      current.deleteCharAt(current.length() - 1);
    }
  }

  static void main() {
    System.out.println(removeInvalidParentheses("()())()"));// [(())(), ()()()]
    System.out.println(removeInvalidParentheses("(a)())()"));// [(a())(), (a)()()]
    System.out.println(removeInvalidParentheses(")("));// []
    System.out.println(removeInvalidParentheses("(a)"));// [(a)]
  }
}
