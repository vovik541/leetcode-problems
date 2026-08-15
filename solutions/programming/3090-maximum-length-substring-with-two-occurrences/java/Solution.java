package dev.vkh.solutions;

class Solution {
  public static int maximumLengthSubstring(String s) {
    int[] characterCount = new int[26];

    int left = 0;
    int maxLength = 0;

    for (int right = 0; right < s.length(); right++) {
      int rightCharIndex = s.charAt(right) - 'a';
      characterCount[rightCharIndex]++;

      while (characterCount[rightCharIndex] > 2) {
        int leftCharIndex = s.charAt(left) - 'a';
        characterCount[leftCharIndex]--;
        left++;
      }

      int currentLength = right - left + 1;
      maxLength = Math.max(maxLength, currentLength);
    }

    return maxLength;
  }

  static void main() {
    System.out.println(maximumLengthSubstring("bcbbbcba")); // 4
    System.out.println(maximumLengthSubstring("aaaa")); // 2
  }
}
