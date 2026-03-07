package dev.vkh.solutions;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.minFlips("111000")); // 2
    System.out.println(solution.minFlips("010")); // 0
    System.out.println(solution.minFlips("1110")); // 1
  }

  public int minFlips(String s) {
    int originalLength = s.length();
    String doubledString = s + s;

    int mismatchesWithPatternStartingWithZero = 0;
    int mismatchesWithPatternStartingWithOne = 0;
    int minimumFlips = Integer.MAX_VALUE;

    for (int index = 0; index < doubledString.length(); index++) {
      char currentCharacter = doubledString.charAt(index);

      char expectedCharacterForZeroStart = (index % 2 == 0) ? '0' : '1';
      char expectedCharacterForOneStart = (index % 2 == 0) ? '1' : '0';

      if (currentCharacter != expectedCharacterForZeroStart) {
        mismatchesWithPatternStartingWithZero++;
      }
      if (currentCharacter != expectedCharacterForOneStart) {
        mismatchesWithPatternStartingWithOne++;
      }

      if (index >= originalLength) {
        char outgoingCharacter = doubledString.charAt(index - originalLength);

        char outgoingExpectedForZeroStart = ((index - originalLength) % 2 == 0) ? '0' : '1';
        char outgoingExpectedForOneStart = ((index - originalLength) % 2 == 0) ? '1' : '0';

        if (outgoingCharacter != outgoingExpectedForZeroStart) {
          mismatchesWithPatternStartingWithZero--;
        }
        if (outgoingCharacter != outgoingExpectedForOneStart) {
          mismatchesWithPatternStartingWithOne--;
        }
      }

      if (index >= originalLength - 1) {
        minimumFlips =
            Math.min(
                minimumFlips,
                Math.min(
                    mismatchesWithPatternStartingWithZero, mismatchesWithPatternStartingWithOne));
      }
    }

    return minimumFlips;
  }
}
