package dev.vkh.solutions;

class Solution {
  public static int minimumPushes(String word) {
    int length = word.length();
    int eightsInLength = length / 8;
    int result = 0;

    for (int i = 1; i <= eightsInLength; i++) {
      result += 8 * i;
    }

    return result + (eightsInLength + 1) * (length % 8);
  }

  static void main() {
    System.out.println(minimumPushes("abcde")); // 5
    System.out.println(minimumPushes("xycdefghij")); // 12
  }
}
