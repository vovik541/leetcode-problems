package dev.vkh.solutions;

class Solution {
  public boolean checkStrings(String firstString, String secondString) {
    int[] evenPositionDifference = new int[26];
    int[] oddPositionDifference = new int[26];

    for (int index = 0; index < firstString.length(); index++) {
      char firstCharacter = firstString.charAt(index);
      char secondCharacter = secondString.charAt(index);

      if (index % 2 == 0) {
        evenPositionDifference[firstCharacter - 'a']++;
        evenPositionDifference[secondCharacter - 'a']--;
      } else {
        oddPositionDifference[firstCharacter - 'a']++;
        oddPositionDifference[secondCharacter - 'a']--;
      }
    }

    for (int letterIndex = 0; letterIndex < 26; letterIndex++) {
      if (evenPositionDifference[letterIndex] != 0 || oddPositionDifference[letterIndex] != 0) {
        return false;
      }
    }

    return true;
  }

  static void main() {
    System.out.println(new Solution().checkStrings("abcdba", "cabdab"));
  }
}
