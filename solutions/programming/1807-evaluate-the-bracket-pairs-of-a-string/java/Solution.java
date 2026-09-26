package dev.vkh.solutions;

import java.util.*;

class Solution {
  public String evaluate(String s, List<List<String>> knowledge) {
    Map<String, String> values = new HashMap<>();

    for (List<String> pair : knowledge) {
      values.put(pair.get(0), pair.get(1));
    }

    StringBuilder result = new StringBuilder();
    int index = 0;

    while (index < s.length()) {
      if (s.charAt(index) != '(') {
        result.append(s.charAt(index));
        index++;
        continue;
      }

      int closingIndex = s.indexOf(')', index);
      String key = s.substring(index + 1, closingIndex);

      result.append(values.getOrDefault(key, "?"));

      index = closingIndex + 1;
    }

    return result.toString();
  }
}
