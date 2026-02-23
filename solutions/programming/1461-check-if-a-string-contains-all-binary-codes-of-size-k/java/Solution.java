package dev.vkh.solutions;

public class Solution {
  public static boolean hasAllCodes(String s, int k) {
    int stringLength = s.length();
    if (stringLength < k) {
      return false;
    }

    int totalCodes = 1 << k;
    int totalWindows = stringLength - k + 1;
    if (totalWindows < totalCodes) {
      return false;
    }

    boolean[] seenCodes = new boolean[totalCodes];
    int seenCount = 0;

    int mask = totalCodes - 1;
    int windowValue = 0;

    for (int index = 0; index < stringLength; index++) {
      int bit = s.charAt(index) - '0';
      windowValue = ((windowValue << 1) & mask) | bit;

      if (index >= k - 1) {
        if (!seenCodes[windowValue]) {
          seenCodes[windowValue] = true;
          seenCount++;
          if (seenCount == totalCodes) {
            return true;
          }
        }
      }
    }

    return false;
  }

  static void main() {
    System.out.println(new Solution().hasAllCodes("00110110", 2));
  }
}
