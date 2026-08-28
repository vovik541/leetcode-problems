package dev.vkh.solutions;

class Solution {

  public static String smallestPalindrome(String s, String target) {
    int[] characterFrequency = new int[26];

    for (char character : s.toCharArray()) {
      characterFrequency[character - 'a']++;
    }

    int oddFrequencyCount = 0;
    char middleCharacter = '\0';

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      if ((characterFrequency[characterIndex] & 1) == 1) {
        oddFrequencyCount++;
        middleCharacter = (char) ('a' + characterIndex);
      }
    }

    if ((s.length() % 2 == 0 && oddFrequencyCount != 0)
        || (s.length() % 2 == 1 && oddFrequencyCount != 1)) {
      return "";
    }

    int halfLength = s.length() / 2;
    int[] halfFrequency = new int[26];

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      halfFrequency[characterIndex] = characterFrequency[characterIndex] / 2;
    }

    String targetFirstHalf = target.substring(0, halfLength);

    if (canBuildExactHalf(targetFirstHalf, halfFrequency)) {
      String exactPalindrome = buildPalindrome(targetFirstHalf, middleCharacter, s.length());

      if (exactPalindrome.compareTo(target) > 0) {
        return exactPalindrome;
      }
    }

    String greaterFirstHalf = findSmallestGreaterPermutation(halfFrequency, targetFirstHalf);

    if (greaterFirstHalf.isEmpty()) {
      return "";
    }

    return buildPalindrome(greaterFirstHalf, middleCharacter, s.length());
  }

  private static boolean canBuildExactHalf(String targetFirstHalf, int[] halfFrequency) {
    int[] requiredFrequency = new int[26];

    for (char character : targetFirstHalf.toCharArray()) {
      requiredFrequency[character - 'a']++;
    }

    for (int characterIndex = 0; characterIndex < 26; characterIndex++) {
      if (requiredFrequency[characterIndex] != halfFrequency[characterIndex]) {
        return false;
      }
    }

    return true;
  }

  private static String findSmallestGreaterPermutation(
      int[] originalFrequency, String targetFirstHalf) {

    int[] availableFrequency = originalFrequency.clone();
    char[] result = new char[targetFirstHalf.length()];

    int currentIndex = 0;

    while (currentIndex < targetFirstHalf.length()) {
      int targetCharacterIndex = targetFirstHalf.charAt(currentIndex) - 'a';

      if (availableFrequency[targetCharacterIndex] > 0) {
        result[currentIndex] = targetFirstHalf.charAt(currentIndex);
        availableFrequency[targetCharacterIndex]--;
        currentIndex++;
        continue;
      }

      int greaterCharacterIndex =
          findNextGreaterCharacter(availableFrequency, targetCharacterIndex);

      if (greaterCharacterIndex != -1) {
        result[currentIndex] = (char) ('a' + greaterCharacterIndex);
        availableFrequency[greaterCharacterIndex]--;

        fillRemainingCharacters(result, currentIndex + 1, availableFrequency);
        return new String(result);
      }

      break;
    }

    for (int index = currentIndex - 1; index >= 0; index--) {
      int restoredCharacterIndex = result[index] - 'a';
      availableFrequency[restoredCharacterIndex]++;

      int targetCharacterIndex = targetFirstHalf.charAt(index) - 'a';

      int greaterCharacterIndex =
          findNextGreaterCharacter(availableFrequency, targetCharacterIndex);

      if (greaterCharacterIndex == -1) {
        continue;
      }

      result[index] = (char) ('a' + greaterCharacterIndex);
      availableFrequency[greaterCharacterIndex]--;

      fillRemainingCharacters(result, index + 1, availableFrequency);

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

  private static String buildPalindrome(String firstHalf, char middleCharacter, int totalLength) {

    StringBuilder palindrome = new StringBuilder(totalLength);

    palindrome.append(firstHalf);

    if ((totalLength & 1) == 1) {
      palindrome.append(middleCharacter);
    }

    for (int index = firstHalf.length() - 1; index >= 0; index--) {
      palindrome.append(firstHalf.charAt(index));
    }

    return palindrome.toString();
  }

  static void main() {
    System.out.println(smallestPalindrome("baba", "abba")); // baab
    System.out.println(smallestPalindrome("baba", "bbaa")); // ""
    System.out.println(smallestPalindrome("abc", "abb")); // ""
    System.out.println(smallestPalindrome("aac", "abb")); // aca
  }
}
