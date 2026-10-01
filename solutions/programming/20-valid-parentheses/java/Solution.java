package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class Solution {

  private static final Map<Character, Character> rules =
      new HashMap<>() {
        {
          put(')', '(');
          put('}', '{');
          put(']', '[');
        }
      };

  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
        continue;
      }

      if (stack.isEmpty()) return false;
      if (rules.get(c) != stack.pop()) return false;
    }

    return stack.isEmpty();
  }

  static void main() {
    System.out.println(isValid("()")); // true
    System.out.println(isValid("()[]{}")); // true
    System.out.println(isValid("(]")); // false
    System.out.println(isValid("([])")); // true
    System.out.println(isValid("([)]")); // false
  }
}
