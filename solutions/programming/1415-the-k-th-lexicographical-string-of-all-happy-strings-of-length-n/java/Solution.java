package dev.vkh.solutions;

class Solution {

  public String getHappyString(int n, int k) {
    int totalHappyStrings = 3 * (1 << (n - 1));
    if (k > totalHappyStrings) {
      return "";
    }

    StringBuilder happyStringBuilder = new StringBuilder();
    char previousCharacter = '\0';

    for (int position = 0; position < n; position++) {
      for (char currentCharacter = 'a'; currentCharacter <= 'c'; currentCharacter++) {
        if (currentCharacter == previousCharacter) {
          continue;
        }

        int remainingLength = n - position - 1;
        int happyStringsStartingWithCurrentPrefix = 1 << remainingLength;

        if (k > happyStringsStartingWithCurrentPrefix) {
          k -= happyStringsStartingWithCurrentPrefix;
        } else {
          happyStringBuilder.append(currentCharacter);
          previousCharacter = currentCharacter;
          break;
        }
      }
    }

    return happyStringBuilder.toString();
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.getHappyString(1, 3)); // c
    System.out.println(solution.getHappyString(1, 4)); // ""
    System.out.println(solution.getHappyString(3, 9)); // cab
  }
}
