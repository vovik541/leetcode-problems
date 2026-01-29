package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public long minimumCost(
      String source, String target, char[] original, char[] changed, int[] cost) {
    final int alphabetSize = 26;
    final long infinityDistance = Long.MAX_VALUE / 4;

    long[][] minCostBetweenLetters = new long[alphabetSize][alphabetSize];
    for (int fromLetter = 0; fromLetter < alphabetSize; fromLetter++) {
      Arrays.fill(minCostBetweenLetters[fromLetter], infinityDistance);
      minCostBetweenLetters[fromLetter][fromLetter] = 0;
    }

    for (int ruleIndex = 0; ruleIndex < cost.length; ruleIndex++) {
      int fromLetterIndex = original[ruleIndex] - 'a';
      int toLetterIndex = changed[ruleIndex] - 'a';
      long ruleCost = cost[ruleIndex];

      if (ruleCost < minCostBetweenLetters[fromLetterIndex][toLetterIndex]) {
        minCostBetweenLetters[fromLetterIndex][toLetterIndex] = ruleCost;
      }
    }

    for (int middleLetter = 0; middleLetter < alphabetSize; middleLetter++) {
      for (int fromLetter = 0; fromLetter < alphabetSize; fromLetter++) {
        long distanceFromToMiddle = minCostBetweenLetters[fromLetter][middleLetter];
        if (distanceFromToMiddle >= infinityDistance) continue;

        for (int toLetter = 0; toLetter < alphabetSize; toLetter++) {
          long distanceMiddleToTo = minCostBetweenLetters[middleLetter][toLetter];
          if (distanceMiddleToTo >= infinityDistance) continue;

          long candidateDistance = distanceFromToMiddle + distanceMiddleToTo;
          if (candidateDistance < minCostBetweenLetters[fromLetter][toLetter]) {
            minCostBetweenLetters[fromLetter][toLetter] = candidateDistance;
          }
        }
      }
    }

    long totalCost = 0;
    int stringLength = source.length();
    for (int position = 0; position < stringLength; position++) {
      char sourceChar = source.charAt(position);
      char targetChar = target.charAt(position);

      if (sourceChar == targetChar) continue;

      long positionCost = minCostBetweenLetters[sourceChar - 'a'][targetChar - 'a'];
      if (positionCost >= infinityDistance) {
        return -1;
      }
      totalCost += positionCost;
    }

    return totalCost;
  }

  static void main() {
    Solution solution = new Solution();

    String source = "abcd";
    String target = "acbe";
    char[] original = {'a', 'b', 'c', 'c', 'e', 'd'};
    char[] changed = {'b', 'c', 'b', 'e', 'b', 'e'};
    int[] cost = {2, 5, 5, 1, 2, 20};
    System.out.println(solution.minimumCost(source, target, original, changed, cost));
  }
}
