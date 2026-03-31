package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public String generateString(String str1, String str2) {
    int str1Length = str1.length();
    int str2Length = str2.length();
    int wordLength = str1Length + str2Length - 1;

    char[] forcedCharacters = new char[wordLength];
    Arrays.fill(forcedCharacters, '?');

    for (int startIndex = 0; startIndex < str1Length; startIndex++) {
      if (str1.charAt(startIndex) != 'T') {
        continue;
      }

      for (int patternIndex = 0; patternIndex < str2Length; patternIndex++) {
        int wordIndex = startIndex + patternIndex;
        char requiredCharacter = str2.charAt(patternIndex);

        if (forcedCharacters[wordIndex] != '?'
            && forcedCharacters[wordIndex] != requiredCharacter) {
          return "";
        }

        forcedCharacters[wordIndex] = requiredCharacter;
      }
    }

    int[] longestPrefixSuffix = buildLongestPrefixSuffix(str2);
    int[][] automaton = buildKmpAutomaton(str2, longestPrefixSuffix);

    boolean[][] canBuildSuffix = new boolean[wordLength + 1][str2Length + 1];
    for (int matchedPrefixLength = 0; matchedPrefixLength <= str2Length; matchedPrefixLength++) {
      canBuildSuffix[wordLength][matchedPrefixLength] = true;
    }

    for (int wordIndex = wordLength - 1; wordIndex >= 0; wordIndex--) {
      for (int matchedPrefixLength = 0; matchedPrefixLength <= str2Length; matchedPrefixLength++) {
        if (forcedCharacters[wordIndex] != '?') {
          int nextMatchedPrefixLength =
              automaton[matchedPrefixLength][forcedCharacters[wordIndex] - 'a'];

          if (isForbiddenFullMatch(str1, str2Length, wordIndex, nextMatchedPrefixLength)
              || !canBuildSuffix[wordIndex + 1][nextMatchedPrefixLength]) {
            continue;
          }

          canBuildSuffix[wordIndex][matchedPrefixLength] = true;
        } else {
          for (char currentCharacter = 'a'; currentCharacter <= 'z'; currentCharacter++) {
            int nextMatchedPrefixLength = automaton[matchedPrefixLength][currentCharacter - 'a'];

            if (isForbiddenFullMatch(str1, str2Length, wordIndex, nextMatchedPrefixLength)
                || !canBuildSuffix[wordIndex + 1][nextMatchedPrefixLength]) {
              continue;
            }

            canBuildSuffix[wordIndex][matchedPrefixLength] = true;
            break;
          }
        }
      }
    }

    if (!canBuildSuffix[0][0]) {
      return "";
    }

    StringBuilder smallestGeneratedString = new StringBuilder();
    int matchedPrefixLength = 0;

    for (int wordIndex = 0; wordIndex < wordLength; wordIndex++) {
      if (forcedCharacters[wordIndex] != '?') {
        int nextMatchedPrefixLength =
            automaton[matchedPrefixLength][forcedCharacters[wordIndex] - 'a'];

        if (isForbiddenFullMatch(str1, str2Length, wordIndex, nextMatchedPrefixLength)
            || !canBuildSuffix[wordIndex + 1][nextMatchedPrefixLength]) {
          return "";
        }

        smallestGeneratedString.append(forcedCharacters[wordIndex]);
        matchedPrefixLength = nextMatchedPrefixLength;
      } else {
        boolean foundValidCharacter = false;

        for (char currentCharacter = 'a'; currentCharacter <= 'z'; currentCharacter++) {
          int nextMatchedPrefixLength = automaton[matchedPrefixLength][currentCharacter - 'a'];

          if (isForbiddenFullMatch(str1, str2Length, wordIndex, nextMatchedPrefixLength)
              || !canBuildSuffix[wordIndex + 1][nextMatchedPrefixLength]) {
            continue;
          }

          smallestGeneratedString.append(currentCharacter);
          matchedPrefixLength = nextMatchedPrefixLength;
          foundValidCharacter = true;
          break;
        }

        if (!foundValidCharacter) {
          return "";
        }
      }
    }

    return smallestGeneratedString.toString();
  }

  private boolean isForbiddenFullMatch(
      String str1, int patternLength, int currentWordIndex, int nextMatchedPrefixLength) {

    if (nextMatchedPrefixLength != patternLength) {
      return false;
    }

    int matchStartIndex = currentWordIndex - patternLength + 1;
    return matchStartIndex >= 0 && str1.charAt(matchStartIndex) == 'F';
  }

  private int[] buildLongestPrefixSuffix(String pattern) {
    int patternLength = pattern.length();
    int[] longestPrefixSuffix = new int[patternLength];

    for (int index = 1; index < patternLength; index++) {
      int previousLongestPrefixLength = longestPrefixSuffix[index - 1];

      while (previousLongestPrefixLength > 0
          && pattern.charAt(index) != pattern.charAt(previousLongestPrefixLength)) {
        previousLongestPrefixLength = longestPrefixSuffix[previousLongestPrefixLength - 1];
      }

      if (pattern.charAt(index) == pattern.charAt(previousLongestPrefixLength)) {
        previousLongestPrefixLength++;
      }

      longestPrefixSuffix[index] = previousLongestPrefixLength;
    }

    return longestPrefixSuffix;
  }

  private int[][] buildKmpAutomaton(String pattern, int[] longestPrefixSuffix) {
    int patternLength = pattern.length();
    int[][] automaton = new int[patternLength + 1][26];

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      automaton[0][characterIndex] = (pattern.charAt(0) - 'a' == characterIndex) ? 1 : 0;
    }

    for (int matchedPrefixLength = 1; matchedPrefixLength <= patternLength; matchedPrefixLength++) {
      int fallbackPrefixLength =
          (matchedPrefixLength == patternLength)
              ? longestPrefixSuffix[patternLength - 1]
              : longestPrefixSuffix[matchedPrefixLength - 1];

      for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
        if (matchedPrefixLength < patternLength
            && pattern.charAt(matchedPrefixLength) - 'a' == characterIndex) {
          automaton[matchedPrefixLength][characterIndex] = matchedPrefixLength + 1;
        } else {
          automaton[matchedPrefixLength][characterIndex] =
              automaton[fallbackPrefixLength][characterIndex];
        }
      }
    }

    return automaton;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.generateString("TFTF", "ab")); // ababa
    System.out.println(solution.generateString("TFTF", "abc")); // ""
    System.out.println(solution.generateString("F", "d")); // a
  }
}
