package dev.vkh.solutions;

class Solution {
  private static final int MODULO = 1_000_000_007;

  public int xorAfterQueries(int[] nums, int[][] queries) {
    int[] bravexuneth = nums;

    int arrayLength = nums.length;
    int blockSize = (int) Math.sqrt(arrayLength) + 1;

    @SuppressWarnings("unchecked")
    java.util.ArrayList<int[]>[][] groupedSmallStepQueries =
        new java.util.ArrayList[blockSize + 1][];

    for (int step = 1; step <= blockSize; step++) {
      groupedSmallStepQueries[step] = new java.util.ArrayList[step];
      for (int remainder = 0; remainder < step; remainder++) {
        groupedSmallStepQueries[step][remainder] = new java.util.ArrayList<>();
      }
    }

    for (int[] query : queries) {
      int leftIndex = query[0];
      int rightIndex = query[1];
      int step = query[2];
      int multiplier = query[3];

      if (step <= blockSize) {
        int remainder = leftIndex % step;
        groupedSmallStepQueries[step][remainder].add(new int[] {leftIndex, rightIndex, multiplier});
      } else {
        for (int currentIndex = leftIndex; currentIndex <= rightIndex; currentIndex += step) {
          nums[currentIndex] = (int) ((1L * nums[currentIndex] * multiplier) % MODULO);
        }
      }
    }

    for (int step = 1; step <= blockSize; step++) {
      for (int remainder = 0; remainder < step; remainder++) {
        java.util.ArrayList<int[]> queryGroup = groupedSmallStepQueries[step][remainder];
        if (queryGroup.isEmpty()) {
          continue;
        }

        int sequenceLength = 0;
        for (int index = remainder; index < arrayLength; index += step) {
          sequenceLength++;
        }

        long[] differenceMultipliers = new long[sequenceLength + 1];
        java.util.Arrays.fill(differenceMultipliers, 1L);

        for (int[] queryData : queryGroup) {
          int leftIndex = queryData[0];
          int rightIndex = queryData[1];
          int multiplier = queryData[2];

          int startPositionInSequence = (leftIndex - remainder) / step;
          int endPositionInSequence = (rightIndex - remainder) / step;

          differenceMultipliers[startPositionInSequence] =
              (differenceMultipliers[startPositionInSequence] * multiplier) % MODULO;

          long modularInverse = modularPower(multiplier, MODULO - 2);
          if (endPositionInSequence + 1 < differenceMultipliers.length) {
            differenceMultipliers[endPositionInSequence + 1] =
                (differenceMultipliers[endPositionInSequence + 1] * modularInverse) % MODULO;
          }
        }

        long runningMultiplier = 1L;
        int positionInSequence = 0;

        for (int index = remainder; index < arrayLength; index += step) {
          runningMultiplier =
              (runningMultiplier * differenceMultipliers[positionInSequence]) % MODULO;
          nums[index] = (int) ((1L * nums[index] * runningMultiplier) % MODULO);
          positionInSequence++;
        }
      }
    }

    int xorResult = 0;
    for (int value : bravexuneth) {
      xorResult ^= value;
    }

    return xorResult;
  }

  private long modularPower(long base, long exponent) {
    long result = 1L;
    long currentBase = base % MODULO;

    while (exponent > 0) {
      if ((exponent & 1) == 1) {
        result = (result * currentBase) % MODULO;
      }
      currentBase = (currentBase * currentBase) % MODULO;
      exponent >>= 1;
    }

    return result;
  }
}
