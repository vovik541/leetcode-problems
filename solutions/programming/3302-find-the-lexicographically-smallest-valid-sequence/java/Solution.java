package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static int[] validSequence(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    int[] suffixMatch = buildSuffixMatch(word1, word2);

    int[] answer = new int[m];

    int word2Index = 0;
    int answerIndex = 0;
    boolean mismatchUsed = false;

    for (int word1Index = 0; word1Index < n && word2Index < m; word1Index++) {
      char currentChar = word1.charAt(word1Index);
      char targetChar = word2.charAt(word2Index);

      if (currentChar == targetChar) {
        answer[answerIndex++] = word1Index;
        word2Index++;
      } else if (!mismatchUsed && suffixMatch[word1Index + 1] >= m - word2Index - 1) {
        answer[answerIndex++] = word1Index;
        word2Index++;
        mismatchUsed = true;
      }
    }

    if (word2Index != m) {
      return new int[0];
    }

    return answer;
  }

  private static int[] buildSuffixMatch(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    int[] suffixMatch = new int[n + 1];

    for (int index = n - 1; index >= 0; index--) {
      suffixMatch[index] = suffixMatch[index + 1];

      int alreadyMatched = suffixMatch[index + 1];

      if (alreadyMatched < m && word1.charAt(index) == word2.charAt(m - alreadyMatched - 1)) {
        suffixMatch[index] = alreadyMatched + 1;
      }
    }

    return suffixMatch;
  }

  static void main() {
    System.out.println(Arrays.toString(validSequence("vbcca", "abc"))); // [0, 1, 2]
    System.out.println(Arrays.toString(validSequence("bacdc", "abc"))); // [1, 2, 4]
    System.out.println(Arrays.toString(validSequence("aaaaaa", "aaabc"))); // []
    System.out.println(Arrays.toString(validSequence("abc", "ab"))); // [0, 1]
  }
}
