package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

  public String makeLargestSpecial(String s) {
    List<String> specialBlocks = new ArrayList<>();

    int balance = 0;
    int blockStartIndex = 0;

    for (int currentIndex = 0; currentIndex < s.length(); currentIndex++) {
      char currentChar = s.charAt(currentIndex);
      if (currentChar == '1') {
        balance++;
      } else {
        balance--;
      }

      if (balance == 0) {
        String innerPart = s.substring(blockStartIndex + 1, currentIndex);
        String bestInnerPart = makeLargestSpecial(innerPart);

        String rebuiltBlock = "1" + bestInnerPart + "0";
        specialBlocks.add(rebuiltBlock);

        blockStartIndex = currentIndex + 1;
      }
    }

    Collections.sort(specialBlocks, Collections.reverseOrder());

      StringBuilder resultBuilder = new StringBuilder();
      for (String block : specialBlocks) {
        resultBuilder.append(block);
      }

      return resultBuilder.toString();
    }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.makeLargestSpecial("11011000"));
    System.out.println(solution.makeLargestSpecial("10"));
  }
}
