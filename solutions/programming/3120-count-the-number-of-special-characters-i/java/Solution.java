package dev.vkh.solutions;

class Solution {

  public int numberOfSpecialChars(String word) {
    boolean[] lowercaseExists = new boolean[26];
    boolean[] uppercaseExists = new boolean[26];

    for (char currentCharacter : word.toCharArray()) {
      if (Character.isLowerCase(currentCharacter)) {
        lowercaseExists[currentCharacter - 'a'] = true;
      } else {
        uppercaseExists[currentCharacter - 'A'] = true;
      }
    }

    int specialCharactersCount = 0;

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      if (lowercaseExists[characterIndex] && uppercaseExists[characterIndex]) {

        specialCharactersCount++;
      }
    }

    return specialCharactersCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numberOfSpecialChars("aaAbcBC")); // 3
  }
}
