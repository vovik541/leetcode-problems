package dev.vkh.solutions;

import java.util.Arrays;

public class Solution {

  public static int minimumPushes(String word) {
    int[] frequency = new int[26];

    for (char currentChar : word.toCharArray()) {
      frequency[currentChar - 'a']++;
    }

    Arrays.sort(frequency);

    int totalPushes = 0;
    int assignedLetters = 0;

    for (int index = 25; index >= 0; index--) {
      if (frequency[index] == 0) {
        break;
      }

      int pushCost = assignedLetters / 8 + 1;

      totalPushes += frequency[index] * pushCost;
      assignedLetters++;
    }

    return totalPushes;
  }

  static void main() {
    System.out.println(minimumPushes("abcde")); // 5
    System.out.println(minimumPushes("xyzxyzxyzxyz")); // 12
    System.out.println(minimumPushes("aabbccddeeffgghhiiiiii")); // 24
  }
}
