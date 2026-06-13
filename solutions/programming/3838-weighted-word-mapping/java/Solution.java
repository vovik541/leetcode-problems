package dev.vkh.solutions;

class Solution {

  public String mapWordWeights(String[] words, int[] weights) {
    StringBuilder mappedCharacters = new StringBuilder();

    for (String word : words) {
      int wordWeight = 0;

      for (char character : word.toCharArray()) {
        int letterIndex = character - 'a';
        wordWeight += weights[letterIndex];
      }

      int remainder = wordWeight % 26;
      char mappedCharacter = (char) ('z' - remainder);
      mappedCharacters.append(mappedCharacter);
    }

    return mappedCharacters.toString();
  }

  static void main() {
    Solution solution = new Solution();

    String[] words1 = {"abcd", "def", "xyz"};
    int[] weights1 = {
      5, 3, 12, 14, 1, 2, 3, 2, 10, 6, 6, 9, 7,
      8, 7, 10, 8, 9, 6, 9, 9, 8, 3, 7, 7, 2
    };

    System.out.println(solution.weightedWordMapping(words1, weights1)); // rij
  }
}
