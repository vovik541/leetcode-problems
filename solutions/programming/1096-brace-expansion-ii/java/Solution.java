package dev.vkh.solutions;

import java.util.*;

class Solution {

  public List<String> braceExpansionII(String expression) {
    Set<String> result = parseExpression(expression, new int[] {0});

    List<String> answer = new ArrayList<>(result);
    Collections.sort(answer);

    return answer;
  }

  private Set<String> parseExpression(String expression, int[] index) {
    Set<String> result = new HashSet<>();

    while (index[0] < expression.length() && expression.charAt(index[0]) != '}') {

      Set<String> term = parseTerm(expression, index);

      result.addAll(term);

      if (index[0] < expression.length() && expression.charAt(index[0]) == ',') {
        index[0]++;
      }
    }

    return result;
  }

  private Set<String> parseTerm(String expression, int[] index) {
    Set<String> result = new HashSet<>();
    result.add("");

    while (index[0] < expression.length()) {
      char current = expression.charAt(index[0]);

      if (current == ',' || current == '}') {
        break;
      }

      Set<String> part;

      if (current == '{') {
        index[0]++;

        part = parseExpression(expression, index);

        index[0]++; // skip '}'
      } else {
        part = new HashSet<>();
        part.add(String.valueOf(current));

        index[0]++;
      }

      result = concatenate(result, part);
    }

    return result;
  }

  private Set<String> concatenate(Set<String> first, Set<String> second) {

    Set<String> result = new HashSet<>();

    for (String left : first) {
      for (String right : second) {
        result.add(left + right);
      }
    }

    return result;
  }
}
