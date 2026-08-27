package dev.vkh.solutions;

class Solution {

  public static String lexGreaterPermutation(String s, String target) {
    int[] characterFrequency = new int[26];

    for (char character : s.toCharArray()) {
      characterFrequency[character - 'a']++;
    }

    char[] result = new char[s.length()];
    int currentIndex = 0;

    while (currentIndex < target.length()) {
      int targetCharacterIndex = target.charAt(currentIndex) - 'a';

      if (characterFrequency[targetCharacterIndex] > 0) {
        result[currentIndex] = target.charAt(currentIndex);
        characterFrequency[targetCharacterIndex]--;
        currentIndex++;
        continue;
      }

      int greaterCharacterIndex =
          findNextGreaterCharacter(characterFrequency, targetCharacterIndex);

      if (greaterCharacterIndex != -1) {
        result[currentIndex] = (char) ('a' + greaterCharacterIndex);
        characterFrequency[greaterCharacterIndex]--;

        fillRemainingCharacters(result, currentIndex + 1, characterFrequency);
        return new String(result);
      }

      break;
    }

    for (int index = currentIndex - 1; index >= 0; index--) {
      int restoredCharacterIndex = result[index] - 'a';
      characterFrequency[restoredCharacterIndex]++;

      int targetCharacterIndex = target.charAt(index) - 'a';
      int greaterCharacterIndex =
          findNextGreaterCharacter(characterFrequency, targetCharacterIndex);

      if (greaterCharacterIndex == -1) {
        continue;
      }

      result[index] = (char) ('a' + greaterCharacterIndex);
      characterFrequency[greaterCharacterIndex]--;

      fillRemainingCharacters(result, index + 1, characterFrequency);

      return new String(result);
    }

    return "";
  }

  private static int findNextGreaterCharacter(int[] characterFrequency, int targetCharacterIndex) {

    for (int characterIndex = targetCharacterIndex + 1; characterIndex < 26; characterIndex++) {

      if (characterFrequency[characterIndex] > 0) {
        return characterIndex;
      }
    }

    return -1;
  }

  private static void fillRemainingCharacters(
      char[] result, int startIndex, int[] characterFrequency) {

    int resultIndex = startIndex;

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      while (characterFrequency[characterIndex] > 0) {
        result[resultIndex++] = (char) ('a' + characterIndex);
        characterFrequency[characterIndex]--;
      }
    }
  }

  static void main() {
    System.out.println(lexGreaterPermutation("abc", "bba")); // bca
    System.out.println(lexGreaterPermutation("leet", "code")); // eelt
    System.out.println(lexGreaterPermutation("baba", "bbaa")); // ""
  }
}
