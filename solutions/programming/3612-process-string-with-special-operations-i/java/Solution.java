package dev.vkh.solutions;

class Solution {

  public String processStr(String s) {
    StringBuilder result = new StringBuilder();

    for (char currentCharacter : s.toCharArray()) {

      if (Character.isLowerCase(currentCharacter)) {
        result.append(currentCharacter);
      } else if (currentCharacter == '*') {
        if (result.length() > 0) {
          result.deleteCharAt(result.length() - 1);
        }
      } else if (currentCharacter == '#') {
        result.append(result);
      } else if (currentCharacter == '%') {
        result.reverse();
      }
    }

    return result.toString();
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.processStr("a#b%*")); // ba
    System.out.println(solution.processStr("z*#")); // ""
  }
}
