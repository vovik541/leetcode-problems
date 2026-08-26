package dev.vkh.solutions;

class Solution {

  public static String shortestBeautifulSubstring(String s, int k) {
    String bestSubstring = "";

    for (int leftIndex = 0; leftIndex < s.length(); leftIndex++) {
      int onesCount = 0;

      for (int rightIndex = leftIndex; rightIndex < s.length(); rightIndex++) {
        if (s.charAt(rightIndex) == '1') {
          onesCount++;
        }

        if (onesCount == k) {
          String currentSubstring = s.substring(leftIndex, rightIndex + 1);

          if (bestSubstring.isEmpty()
              || currentSubstring.length() < bestSubstring.length()
              || (currentSubstring.length() == bestSubstring.length()
                  && currentSubstring.compareTo(bestSubstring) < 0)) {

            bestSubstring = currentSubstring;
          }

          break;
        }

        if (onesCount > k) {
          break;
        }
      }
    }

    return bestSubstring;
  }

  static void main() {
    System.out.println(shortestBeautifulSubstring("100011001", 3)); // 11001
    System.out.println(shortestBeautifulSubstring("1011", 2)); // 11
    System.out.println(shortestBeautifulSubstring("000", 1)); // ""
  }
}
