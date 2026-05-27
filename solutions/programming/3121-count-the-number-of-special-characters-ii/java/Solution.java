package dev.vkh.solutions;

class Solution {

  public int numberOfSpecialChars(String word) {
    int[] lastLowercaseIndex = new int[26];
    int[] firstUppercaseIndex = new int[26];

    for (int index = 0; index < 26; index++) {
      lastLowercaseIndex[index] = -1;
      firstUppercaseIndex[index] = Integer.MAX_VALUE;
    }

    for (int index = 0; index < word.length(); index++) {
      char currentCharacter = word.charAt(index);

      if (Character.isLowerCase(currentCharacter)) {
        int characterIndex = currentCharacter - 'a';
        lastLowercaseIndex[characterIndex] = index;
      } else {
        int characterIndex = currentCharacter - 'A';
        firstUppercaseIndex[characterIndex] = Math.min(firstUppercaseIndex[characterIndex], index);
      }
    }

    int specialCharactersCount = 0;

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      boolean lowercaseExists = lastLowercaseIndex[characterIndex] != -1;
      boolean uppercaseExists = firstUppercaseIndex[characterIndex] != Integer.MAX_VALUE;

      if (lowercaseExists
          && uppercaseExists
          && lastLowercaseIndex[characterIndex] < firstUppercaseIndex[characterIndex]) {

        specialCharactersCount++;
      }
    }

    return specialCharactersCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numberOfSpecialChars("aaAbcBC")); // 3
    System.out.println(solution.numberOfSpecialChars("abc")); // 0
  }
}
