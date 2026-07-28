package dev.vkh.solutions;

public class Solution {

  public static String smallestPalindrome(String s) {
    int[] characterCount = new int[26];

    for (char currentChar : s.toCharArray()) {
      characterCount[currentChar - 'a']++;
    }

    StringBuilder leftHalf = new StringBuilder();
    char middleChar = 0;

    for (int index = 0; index < 26; index++) {
      char currentChar = (char) ('a' + index);

      if (characterCount[index] % 2 == 1) {
        middleChar = currentChar;
      }

      int halfCount = characterCount[index] / 2;

      for (int count = 0; count < halfCount; count++) {
        leftHalf.append(currentChar);
      }
    }

    StringBuilder result = new StringBuilder();

    result.append(leftHalf);

    if (middleChar != 0) {
      result.append(middleChar);
    }

    result.append(new StringBuilder(leftHalf).reverse());

    return result.toString();
  }

  static void main() {
    System.out.println(smallestPalindrome("z")); // Output: z
    System.out.println(smallestPalindrome("babab")); // Output: abbba
    System.out.println(smallestPalindrome("daccad")); // Output: acddca
  }
}
