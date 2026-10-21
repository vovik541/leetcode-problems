package dev.vkh.solutions;

class Solution {

  public static int minOperations(String s) {
    int length = s.length();

    boolean isSorted = true;
    char minChar = 'z';
    char maxChar = 'a';

    for (int i = 0; i < length; i++) {
      char current = s.charAt(i);

      minChar = (char) Math.min(minChar, current);
      maxChar = (char) Math.max(maxChar, current);

      if (i > 0 && s.charAt(i - 1) > current) {
        isSorted = false;
      }
    }

    if (isSorted) {
      return 0;
    }

    if (length == 2) {
      return -1;
    }

    if (s.charAt(0) == minChar || s.charAt(length - 1) == maxChar) {
      return 1;
    }

    boolean minInProperPrefix = false;
    boolean maxInProperSuffix = false;

    for (int i = 0; i < length - 1; i++) {
      if (s.charAt(i) == minChar) {
        minInProperPrefix = true;
      }
    }

    for (int i = 1; i < length; i++) {
      if (s.charAt(i) == maxChar) {
        maxInProperSuffix = true;
      }
    }

    if (minInProperPrefix || maxInProperSuffix) {
      return 2;
    }

    return 3;
  }

  static void main() {
    System.out.println(minOperations("dog")); // 1
    System.out.println(minOperations("card")); // 2
    System.out.println(minOperations("gf")); // -1
    System.out.println(minOperations("abc")); // 0
    System.out.println(minOperations("cba")); // 3
    System.out.println(minOperations("bac")); // 1
    System.out.println(minOperations("bca")); // 2
    System.out.println(minOperations("a")); // 0
  }
}
