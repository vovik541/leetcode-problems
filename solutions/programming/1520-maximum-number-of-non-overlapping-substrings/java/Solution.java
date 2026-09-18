package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

  public static List<String> maxNumOfSubstrings(String s) {
    int[] firstOccurrence = new int[26];
    int[] lastOccurrence = new int[26];

    Arrays.fill(firstOccurrence, -1);

    for (int index = 0; index < s.length(); index++) {
      int characterIndex = s.charAt(index) - 'a';

      if (firstOccurrence[characterIndex] == -1) {
        firstOccurrence[characterIndex] = index;
      }

      lastOccurrence[characterIndex] = index;
    }

    List<String> result = new ArrayList<>();
    int previousEnd = -1;

    for (int startIndex = 0; startIndex < s.length(); startIndex++) {
      int characterIndex = s.charAt(startIndex) - 'a';

      if (firstOccurrence[characterIndex] != startIndex) {
        continue;
      }

      int endIndex = findValidEnd(s, startIndex, firstOccurrence, lastOccurrence);

      if (endIndex == -1) {
        continue;
      }

      if (startIndex > previousEnd) {
        result.add(s.substring(startIndex, endIndex + 1));
      } else {
        result.set(result.size() - 1, s.substring(startIndex, endIndex + 1));
      }

      previousEnd = endIndex;
    }

    return result;
  }

  private static int findValidEnd(
      String s, int startIndex, int[] firstOccurrence, int[] lastOccurrence) {

    int endIndex = lastOccurrence[s.charAt(startIndex) - 'a'];

    for (int index = startIndex; index <= endIndex; index++) {
      int characterIndex = s.charAt(index) - 'a';

      if (firstOccurrence[characterIndex] < startIndex) {
        return -1;
      }

      endIndex = Math.max(endIndex, lastOccurrence[characterIndex]);
    }

    return endIndex;
  }

  static void main() {
    System.out.println(maxNumOfSubstrings("adefaddaccc"));// [e, f, ccc]
    System.out.println(maxNumOfSubstrings("abbaccd"));// [bb, cc, d]
    System.out.println(maxNumOfSubstrings("abab"));// [abab]
    System.out.println(maxNumOfSubstrings("cabcccbaa"));// [cabcccbaa]
  }
}
