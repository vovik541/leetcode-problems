package dev.vkh.solutions;

class Solution {

  public long totalWaviness(long num1, long num2) {
    return countTotalWavinessUpTo(num2) - countTotalWavinessUpTo(num1 - 1);
  }

  private long countTotalWavinessUpTo(long limit) {
    if (limit <= 0) {
      return 0L;
    }

    char[] digits = String.valueOf(limit).toCharArray();
    Long[][][][][] memo = new Long[digits.length][11][11][2][2];

    return digitDp(0, 10, 10, false, true, digits, memo);
  }

  private long digitDp(
      int position,
      int secondLastDigit,
      int lastDigit,
      boolean hasStarted,
      boolean isTight,
      char[] digits,
      Long[][][][][] memo) {
    if (position == digits.length) {
      return 0L;
    }

    int startedIndex = hasStarted ? 1 : 0;
    int tightIndex = isTight ? 1 : 0;

    if (memo[position][secondLastDigit][lastDigit][startedIndex][tightIndex] != null) {
      return memo[position][secondLastDigit][lastDigit][startedIndex][tightIndex];
    }

    int maxDigit = isTight ? digits[position] - '0' : 9;
    long totalWaviness = 0L;

    for (int currentDigit = 0; currentDigit <= maxDigit; currentDigit++) {
      boolean nextIsTight = isTight && currentDigit == maxDigit;

      if (!hasStarted && currentDigit == 0) {
        totalWaviness += digitDp(position + 1, 10, 10, false, nextIsTight, digits, memo);
        continue;
      }

      int addedWaviness = 0;

      if (hasStarted && secondLastDigit != 10) {
        boolean isPeak = lastDigit > secondLastDigit && lastDigit > currentDigit;
        boolean isValley = lastDigit < secondLastDigit && lastDigit < currentDigit;

        if (isPeak || isValley) {
          addedWaviness = 1;
        }
      }

      long suffixWaviness =
          digitDp(position + 1, lastDigit, currentDigit, true, nextIsTight, digits, memo);

      long numbersCount =
          countNumbers(position + 1, lastDigit, currentDigit, true, nextIsTight, digits);

      totalWaviness += suffixWaviness + addedWaviness * numbersCount;
    }

    memo[position][secondLastDigit][lastDigit][startedIndex][tightIndex] = totalWaviness;
    return totalWaviness;
  }

  private long countNumbers(
      int position,
      int secondLastDigit,
      int lastDigit,
      boolean hasStarted,
      boolean isTight,
      char[] digits) {
    Long[][][][] memo = new Long[digits.length + 1][11][11][2];
    return countNumbersDp(position, secondLastDigit, lastDigit, hasStarted, isTight, digits, memo);
  }

  private long countNumbersDp(
      int position,
      int secondLastDigit,
      int lastDigit,
      boolean hasStarted,
      boolean isTight,
      char[] digits,
      Long[][][][] memo) {
    if (position == digits.length) {
      return hasStarted ? 1L : 0L;
    }

    int startedIndex = hasStarted ? 1 : 0;

    if (!isTight && memo[position][secondLastDigit][lastDigit][startedIndex] != null) {
      return memo[position][secondLastDigit][lastDigit][startedIndex];
    }

    int maxDigit = isTight ? digits[position] - '0' : 9;
    long numbersCount = 0L;

    for (int currentDigit = 0; currentDigit <= maxDigit; currentDigit++) {
      boolean nextIsTight = isTight && currentDigit == maxDigit;

      if (!hasStarted && currentDigit == 0) {
        numbersCount += countNumbersDp(position + 1, 10, 10, false, nextIsTight, digits, memo);
      } else {
        numbersCount +=
            countNumbersDp(position + 1, lastDigit, currentDigit, true, nextIsTight, digits, memo);
      }
    }

    if (!isTight) {
      memo[position][secondLastDigit][lastDigit][startedIndex] = numbersCount;
    }

    return numbersCount;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.totalWaviness(120L, 130L)); // 3
    System.out.println(solution.totalWaviness(4848L, 4848L)); // 2
  }
}
