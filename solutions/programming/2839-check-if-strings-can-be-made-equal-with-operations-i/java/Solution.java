package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.canBeEqual("abcd", "cdab")); // true
    System.out.println(solution.canBeEqual("abcd", "dacb")); // false
  }

  public boolean canBeEqual(String s1, String s2) {
    char[] s1EvenIndexedCharacters = {s1.charAt(0), s1.charAt(2)};
    char[] s1OddIndexedCharacters = {s1.charAt(1), s1.charAt(3)};

    char[] s2EvenIndexedCharacters = {s2.charAt(0), s2.charAt(2)};
    char[] s2OddIndexedCharacters = {s2.charAt(1), s2.charAt(3)};

    Arrays.sort(s1EvenIndexedCharacters);
    Arrays.sort(s1OddIndexedCharacters);
    Arrays.sort(s2EvenIndexedCharacters);
    Arrays.sort(s2OddIndexedCharacters);

    return Arrays.equals(s1EvenIndexedCharacters, s2EvenIndexedCharacters)
        && Arrays.equals(s1OddIndexedCharacters, s2OddIndexedCharacters);
  }
}
