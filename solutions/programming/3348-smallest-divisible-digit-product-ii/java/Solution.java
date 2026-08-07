package dev.vkh.solutions;

public class Solution {

  private static final int[][] DIGIT_FACTORS = {
    {0, 0, 0, 0}, // 0, unused
    {0, 0, 0, 0}, // 1
    {1, 0, 0, 0}, // 2
    {0, 1, 0, 0}, // 3
    {2, 0, 0, 0}, // 4
    {0, 0, 1, 0}, // 5
    {1, 1, 0, 0}, // 6
    {0, 0, 0, 1}, // 7
    {3, 0, 0, 0}, // 8
    {0, 2, 0, 0} // 9
  };

  private int requiredTwo;
  private int requiredThree;
  private int requiredFive;
  private int requiredSeven;

  private int[][] minDigitsForTwoThree;

  public String smallestNumber(String num, long t) {
    int[] requiredFactors = factorize(t);

    if (requiredFactors == null) {
      return "-1";
    }

    requiredTwo = requiredFactors[0];
    requiredThree = requiredFactors[1];
    requiredFive = requiredFactors[2];
    requiredSeven = requiredFactors[3];

    buildMinDigitsForTwoThree();

    String sameLengthAnswer = buildSameLengthAnswer(num);

    if (sameLengthAnswer != null) {
      return sameLengthAnswer;
    }

    int minRequiredLength = minimumDigitsNeeded(requiredFactors);
    int newLength = Math.max(num.length() + 1, minRequiredLength);

    return buildSmallestSuffix(requiredFactors.clone(), newLength);
  }

  private int[] factorize(long t) {
    int[] factors = new int[4];

    while (t % 2 == 0) {
      factors[0]++;
      t /= 2;
    }

    while (t % 3 == 0) {
      factors[1]++;
      t /= 3;
    }

    while (t % 5 == 0) {
      factors[2]++;
      t /= 5;
    }

    while (t % 7 == 0) {
      factors[3]++;
      t /= 7;
    }

    if (t != 1) {
      return null;
    }

    return factors;
  }

  private void buildMinDigitsForTwoThree() {
    int maxTwo = requiredTwo;
    int maxThree = requiredThree;

    minDigitsForTwoThree = new int[maxTwo + 1][maxThree + 1];

    for (int two = 0; two <= maxTwo; two++) {
      for (int three = 0; three <= maxThree; three++) {
        minDigitsForTwoThree[two][three] = 1_000_000;
      }
    }

    minDigitsForTwoThree[0][0] = 0;

    int[][] usefulDigits = {
      {0, 0}, // 1
      {1, 0}, // 2
      {0, 1}, // 3
      {2, 0}, // 4
      {1, 1}, // 6
      {3, 0}, // 8
      {0, 2} // 9
    };

    for (int two = 0; two <= maxTwo; two++) {
      for (int three = 0; three <= maxThree; three++) {
        if (minDigitsForTwoThree[two][three] == 1_000_000) {
          continue;
        }

        for (int[] digitFactor : usefulDigits) {
          int nextTwo = Math.min(maxTwo, two + digitFactor[0]);
          int nextThree = Math.min(maxThree, three + digitFactor[1]);

          minDigitsForTwoThree[nextTwo][nextThree] =
              Math.min(
                  minDigitsForTwoThree[nextTwo][nextThree], minDigitsForTwoThree[two][three] + 1);
        }
      }
    }
  }

  private String buildSameLengthAnswer(String num) {
    int n = num.length();

    int[][] prefixFactors = new int[n + 1][4];
    boolean[] prefixHasNoZero = new boolean[n + 1];

    prefixHasNoZero[0] = true;

    for (int index = 0; index < n; index++) {
      for (int factor = 0; factor < 4; factor++) {
        prefixFactors[index + 1][factor] = prefixFactors[index][factor];
      }

      char currentChar = num.charAt(index);

      prefixHasNoZero[index + 1] = prefixHasNoZero[index] && currentChar != '0';

      if (currentChar != '0') {
        int digit = currentChar - '0';
        addDigitFactors(prefixFactors[index + 1], digit);
      }
    }

    int[] totalRemainingFactors = getRemainingFactors(prefixFactors[n]);

    if (prefixHasNoZero[n] && isFullyCovered(totalRemainingFactors)) {
      return num;
    }

    for (int index = n - 1; index >= 0; index--) {
      if (!prefixHasNoZero[index]) {
        continue;
      }

      int currentDigit = num.charAt(index) - '0';

      for (int nextDigit = Math.max(1, currentDigit + 1); nextDigit <= 9; nextDigit++) {
        int[] usedFactors = prefixFactors[index].clone();
        addDigitFactors(usedFactors, nextDigit);

        int[] remainingFactors = getRemainingFactors(usedFactors);
        int remainingSlots = n - index - 1;

        if (minimumDigitsNeeded(remainingFactors) <= remainingSlots) {
          StringBuilder answer = new StringBuilder();

          answer.append(num, 0, index);
          answer.append((char) ('0' + nextDigit));
          answer.append(buildSmallestSuffix(remainingFactors, remainingSlots));

          return answer.toString();
        }
      }
    }

    return null;
  }

  private void addDigitFactors(int[] factors, int digit) {
    for (int factor = 0; factor < 4; factor++) {
      factors[factor] += DIGIT_FACTORS[digit][factor];
    }
  }

  private int[] getRemainingFactors(int[] usedFactors) {
    return new int[] {
      Math.max(0, requiredTwo - usedFactors[0]),
      Math.max(0, requiredThree - usedFactors[1]),
      Math.max(0, requiredFive - usedFactors[2]),
      Math.max(0, requiredSeven - usedFactors[3])
    };
  }

  private boolean isFullyCovered(int[] remainingFactors) {
    return remainingFactors[0] == 0
        && remainingFactors[1] == 0
        && remainingFactors[2] == 0
        && remainingFactors[3] == 0;
  }

  private int minimumDigitsNeeded(int[] remainingFactors) {
    int two = remainingFactors[0];
    int three = remainingFactors[1];
    int five = remainingFactors[2];
    int seven = remainingFactors[3];

    return minDigitsForTwoThree[two][three] + five + seven;
  }

  private String buildSmallestSuffix(int[] remainingFactors, int length) {
    StringBuilder suffix = new StringBuilder();

    for (int position = 0; position < length; position++) {
      int remainingSlotsAfterThis = length - position - 1;

      for (int digit = 1; digit <= 9; digit++) {
        int[] nextRemainingFactors = remainingFactors.clone();

        for (int factor = 0; factor < 4; factor++) {
          nextRemainingFactors[factor] =
              Math.max(0, nextRemainingFactors[factor] - DIGIT_FACTORS[digit][factor]);
        }

        if (minimumDigitsNeeded(nextRemainingFactors) <= remainingSlotsAfterThis) {
          suffix.append((char) ('0' + digit));
          remainingFactors = nextRemainingFactors;
          break;
        }
      }
    }

    return suffix.toString();
  }

  static void main() {
    Solution solution = new Solution();
    System.out.println(solution.smallestNumber("1234", 256)); // 1488
    System.out.println(solution.smallestNumber("12355", 50)); // 12355
    System.out.println(solution.smallestNumber("11111", 26)); // -1
    System.out.println(solution.smallestNumber("1000", 1)); // 1111
    System.out.println(solution.smallestNumber("999", 8)); // 1118
  }
}
