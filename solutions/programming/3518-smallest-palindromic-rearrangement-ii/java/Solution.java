package dev.vkh.solutions;

public class Solution {

  public static String smallestPalindrome(String s, int k) {
    int[] fullCount = new int[26];

    for (char currentChar : s.toCharArray()) {
      fullCount[currentChar - 'a']++;
    }

    int[] halfCount = new int[26];
    char middleChar = 0;
    int halfLength = s.length() / 2;

    for (int index = 0; index < 26; index++) {
      if (fullCount[index] % 2 == 1) {
        middleChar = (char) ('a' + index);
      }

      halfCount[index] = fullCount[index] / 2;
    }

    int[][] combinations = buildCombinations(halfLength, k);

    long totalPermutations = countPermutations(halfCount, halfLength, k, combinations);

    if (totalPermutations < k) {
      return "";
    }

    StringBuilder leftHalf = new StringBuilder();
    long remainingK = k;

    for (int position = 0; position < halfLength; position++) {
      for (int charIndex = 0; charIndex < 26; charIndex++) {
        if (halfCount[charIndex] == 0) {
          continue;
        }

        halfCount[charIndex]--;

        long permutationsWithThisChar =
            countPermutations(halfCount, halfLength - position - 1, remainingK, combinations);

        if (permutationsWithThisChar >= remainingK) {
          leftHalf.append((char) ('a' + charIndex));
          break;
        } else {
          remainingK -= permutationsWithThisChar;
          halfCount[charIndex]++;
        }
      }
    }

    StringBuilder result = new StringBuilder();

    result.append(leftHalf);

    if (middleChar != 0) {
      result.append(middleChar);
    }

    result.append(new StringBuilder(leftHalf).reverse());

    return result.toString();
  }

  private static int[][] buildCombinations(int maxN, int cap) {
    int[][] combinations = new int[maxN + 1][];

    for (int n = 0; n <= maxN; n++) {
      combinations[n] = new int[n + 1];
      combinations[n][0] = 1;
      combinations[n][n] = 1;

      for (int r = 1; r < n; r++) {
        long value = (long) combinations[n - 1][r - 1] + combinations[n - 1][r];
        combinations[n][r] = (int) Math.min(value, cap);
      }
    }

    return combinations;
  }

  private static long countPermutations(
      int[] count, int totalLength, long cap, int[][] combinations) {
    long result = 1;
    int remainingPositions = totalLength;

    for (int charIndex = 0; charIndex < 26; charIndex++) {
      int currentCount = count[charIndex];

      if (currentCount == 0) {
        continue;
      }

      result *= combinations[remainingPositions][currentCount];

      if (result >= cap) {
        return cap;
      }

      remainingPositions -= currentCount;
    }

    return result;
  }

  static void main() {
    System.out.println(smallestPalindrome("abba", 2)); // baab
    System.out.println(smallestPalindrome("bacab", 1)); // abcba
    System.out.println(smallestPalindrome("babab", 2)); // babab
  }
}
