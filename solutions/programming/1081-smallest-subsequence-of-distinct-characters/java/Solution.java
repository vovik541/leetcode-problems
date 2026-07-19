package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  public static String smallestSubsequence(String s) {
    int[] lastIndex = new int[26];
    boolean[] used = new boolean[26];

    for (int i = 0; i < s.length(); i++) {
      lastIndex[s.charAt(i) - 'a'] = i;
    }

    Deque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      int currentIndex = currentChar - 'a';

      if (used[currentIndex]) {
        continue;
      }

      while (!stack.isEmpty()
          && stack.peekLast() > currentChar
          && lastIndex[stack.peekLast() - 'a'] > i) {

        char removedChar = stack.pollLast();
        used[removedChar - 'a'] = false;
      }

      stack.offerLast(currentChar);
      used[currentIndex] = true;
    }

    StringBuilder result = new StringBuilder();

    while (!stack.isEmpty()) {
      result.append(stack.pollFirst());
    }

    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println(smallestSubsequence("bcabc")); // abc
    System.out.println(smallestSubsequence("cbacdcbc")); // acdb
  }
}
