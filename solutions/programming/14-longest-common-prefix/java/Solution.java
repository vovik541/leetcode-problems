package dev.vkh.solutions;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public String longestCommonPrefix(String[] strs) {
    Arrays.sort(strs, Comparator.comparingInt(String::length));

    int maxPrefix = 0;

    charCount:
    for (int charIndex = 0; charIndex < strs[0].length(); charIndex++) {
      for (int wordIndex = 0; wordIndex < strs.length; wordIndex++) {
        if (strs[wordIndex].charAt(charIndex) != strs[0].charAt(charIndex)) {
          break charCount;
        }
      }
      maxPrefix++;
    }
    return maxPrefix == 0 ? "" : strs[0].substring(0, maxPrefix);
  }

  static void main() {
    System.out.println(
        new Solution().longestCommonPrefix(new String[] {"flower", "flow", "flight"}));
  }
}
