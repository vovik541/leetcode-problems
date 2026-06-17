package dev.vkh.solutions;

class Solution {

  public char processStr(String s, long k) {
    int operationCount = s.length();
    long[] resultLengthAfterOperation = new long[operationCount];

    long currentLength = 0L;

    for (int index = 0; index < operationCount; index++) {
      char currentCharacter = s.charAt(index);

      if (Character.isLowerCase(currentCharacter)) {
        currentLength++;
      } else if (currentCharacter == '*') {
        if (currentLength > 0) {
          currentLength--;
        }
      } else if (currentCharacter == '#') {
        currentLength *= 2;
      } else if (currentCharacter == '%') {
        // Reverse does not change length.
      }

      resultLengthAfterOperation[index] = currentLength;
    }

    if (k < 0 || k >= currentLength) {
      return '.';
    }

    for (int index = operationCount - 1; index >= 0; index--) {
      char currentCharacter = s.charAt(index);
      long previousLength = index == 0 ? 0L : resultLengthAfterOperation[index - 1];

      if (Character.isLowerCase(currentCharacter)) {
        if (k == previousLength) {
          return currentCharacter;
        }
      } else if (currentCharacter == '*') {
        // Forward '*' removed the last character, so it cannot create current k.
      } else if (currentCharacter == '#') {
        if (previousLength > 0) {
          k %= previousLength;
        }
      } else if (currentCharacter == '%') {
        k = previousLength - 1 - k;
      }
    }

    return '.';
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.processStr("a#b%*", 1)); // a
    System.out.println(solution.processStr("cd%#*#", 3)); // d
    System.out.println(solution.processStr("z*#", 0)); // .
  }

}
