package dev.vkh.solutions;

public class Solution {

  public static int reverseDegree(String s) {
    int result = 0;

    for (int i = 0; i < s.length(); i++) {
      int reversedAlphabetPosition = 'z' - s.charAt(i) + 1;
      int stringPosition = i + 1;

      result += reversedAlphabetPosition * stringPosition;
    }

    return result;
  }

  static void main() {
    System.out.println(reverseDegree("abc")); // 148
    System.out.println(reverseDegree("zaza")); // 160
  }
}
