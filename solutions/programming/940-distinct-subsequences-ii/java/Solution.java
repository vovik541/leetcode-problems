package dev.vkh.solutions;

class Solution {

  private static final int MODULO = 1_000_000_007;

  public static int distinctSubseqII(String s) {
    long totalSubsequences = 0;
    long[] endingWithCharacter = new long[26];

    for (char character : s.toCharArray()) {
      int characterIndex = character - 'a';

      long newSubsequences = (totalSubsequences + 1) % MODULO;

      totalSubsequences =
          (totalSubsequences + newSubsequences - endingWithCharacter[characterIndex] + MODULO)
              % MODULO;

      endingWithCharacter[characterIndex] = newSubsequences;
    }

    return (int) totalSubsequences;
  }

  static void main() {
    System.out.println(distinctSubseqII("abc")); // 7
    System.out.println(distinctSubseqII("aba")); // 6
    System.out.println(distinctSubseqII("aaa")); // 3
  }
}
