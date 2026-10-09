package dev.vkh.solutions;

class Solution {

  public static int minInsertions(String s) {
    int insertions = 0;
    int requiredClosing = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        if (requiredClosing % 2 != 0) {
          insertions++;
          requiredClosing--;
        }

        requiredClosing += 2;
      } else {
        requiredClosing--;

        if (requiredClosing < 0) {
          insertions++;
          requiredClosing = 1;
        }
      }
    }

    return insertions + requiredClosing;
  }

  static void main() {
    System.out.println(minInsertions("(()))")); // 1
    System.out.println(minInsertions("())")); // 0
    System.out.println(minInsertions("))())(")); // 3
    System.out.println(minInsertions("(((")); // 6
    System.out.println(minInsertions(")))))))")); // 5
  }
}
