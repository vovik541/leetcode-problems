package dev.vkh.solutions;

import java.util.Map;

public class Solution {
  static void main() {
    System.out.println(new Solution().romanToInt("LVIII"));
  }

  public int romanToInt(String s) {
    Map<Character, Integer> romanValues =
        Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    int result = 0;

    for (int i = 0; i < s.length(); i++) {
      int current = romanValues.get(s.charAt(i));

      if (i + 1 < s.length()) {
        int next = romanValues.get(s.charAt(i + 1));
        if (current < next) {
          result -= current;
          continue;
        }
      }

      result += current;
    }

    return result;
  }
}
