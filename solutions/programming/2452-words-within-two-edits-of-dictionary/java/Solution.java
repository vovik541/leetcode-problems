package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> twoEditWords(String[] queries, String[] dictionary) {
    List<String> matchingWords = new ArrayList<>();

    for (String queryWord : queries) {
      if (canMatchWithinTwoEdits(queryWord, dictionary)) {
        matchingWords.add(queryWord);
      }
    }

    return matchingWords;
  }

  private boolean canMatchWithinTwoEdits(String queryWord, String[] dictionary) {
    for (String dictionaryWord : dictionary) {
      if (countDifferentCharacters(queryWord, dictionaryWord) <= 2) {
        return true;
      }
    }

    return false;
  }

  private int countDifferentCharacters(String firstWord, String secondWord) {
    int differentCharactersCount = 0;

    for (int characterIndex = 0; characterIndex < firstWord.length(); characterIndex++) {
      if (firstWord.charAt(characterIndex) != secondWord.charAt(characterIndex)) {
        differentCharactersCount++;

        if (differentCharactersCount > 2) {
          return differentCharactersCount;
        }
      }
    }

    return differentCharactersCount;
  }

  static void main() {
    System.out.println(
        new Solution()
            .twoEditWords(
                new String[] {"word", "note", "ants", "wood"},
                new String[] {"wood", "joke", "moat"}));
  }
}
