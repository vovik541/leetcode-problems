package dev.vkh.solutions;

class Solution {

  public static boolean checkValidString(String s) {
    int minimumOpen = 0;
    int maximumOpen = 0;

    for (char current : s.toCharArray()) {
      if (current == '(') {
        minimumOpen++;
        maximumOpen++;
      } else if (current == ')') {
        minimumOpen--;
        maximumOpen--;
      } else {
        // '*' can be ')', empty, or '('
        minimumOpen--;
        maximumOpen++;
      }

      if (maximumOpen < 0) {
        return false;
      }

      minimumOpen = Math.max(minimumOpen, 0);
    }

    return minimumOpen == 0;
  }

  static void main() {
    System.out.println(checkValidString("()")); // true
    System.out.println(checkValidString("(*)")); // true
    System.out.println(checkValidString("(*))")); // true
    System.out.println(checkValidString("(")); // false
    System.out.println(checkValidString("(*()")); // true
    System.out.println(checkValidString(")*(")); // false
  }
}
