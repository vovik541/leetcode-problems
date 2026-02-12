package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
  public int longestBalanced(String s) {
    BalancedMap balancedMap = new BalancedMap(s);

    for (int left = 0; left < s.length(); left++) {
      for (int right = s.length(); right > left; right--) {
        balancedMap.refresh();
        balancedMap.removeLeftAndRight(left, right);
        balancedMap.isBalanced();
      }
    }

    return balancedMap.getLongestBalanced();
  }

  private class BalancedMap {
    private Map<Character, Integer> startingMap = new HashMap<>();
    private Map<Character, Integer> workingMap;
    private int longestBalanced = 1;
    private String s;

    BalancedMap(String s) {
      this.s = s;
      for (Character character : s.toCharArray()) {
        startingMap.put(
            character,
            Objects.nonNull(startingMap.get(character)) ? startingMap.get(character) + 1 : 1);
      }
    }

    public void refresh() {
      workingMap = new HashMap<>(startingMap);
    }

    public void removeLeftAndRight(int left, int right) {
      for (int i = 0; i < left; i++) {
        removeCharacter(s.charAt(i));
      }
      for (int i = right; i < s.length(); i++) {
        removeCharacter(s.charAt(i));
      }
    }

    public boolean isBalanced() {
      int occurrences = (int) workingMap.values().toArray()[0];
      for (Map.Entry<Character, Integer> entry : workingMap.entrySet()) {
        if (occurrences != entry.getValue()) {
          return false;
        }
      }

      if (workingMap.size() * occurrences > longestBalanced) {
        longestBalanced = workingMap.size() * occurrences;
      }

      return true;
    }

    private void removeCharacter(char chr) {
      if (workingMap.get(chr) > 1) {
        workingMap.put(chr, workingMap.get(chr) - 1);
      } else {
        workingMap.remove(chr);
      }
    }

    public int getLongestBalanced() {
      return longestBalanced;
    }
  }

  static void main() {
    System.out.println(new Solution().longestBalanced("abadeceabedaabceaebdbdedecbeadeabcceeedcdcbbdddacacbeaadedcebbaedbbdeddaeaedaeecaeebceacdbdeaacdaabeadbcbdaadabecbbdcadbeecadeaaaadbbbcccaabecbaedddbddabddbaaaebdabdaea"));
  }
}
