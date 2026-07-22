package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

  public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
    int originalOnes = 0;

    for (char currentChar : s.toCharArray()) {
      if (currentChar == '1') {
        originalOnes++;
      }
    }

    RunData runData = buildRuns(s);
    SegmentTree segmentTree = new SegmentTree(runData.fullGain);

    List<Integer> answer = new ArrayList<>();

    for (int queryIndex = 0; queryIndex < queries.length; queryIndex++) {
      int left = queries[queryIndex][0];
      int right = queries[queryIndex][1];

      int leftRun = runData.runIdByIndex[left];
      int rightRun = runData.runIdByIndex[right];

      int maxGain = 0;

      int queryLeftRun = leftRun + 2;
      int queryRightRun = rightRun - 2;

      if (queryLeftRun <= queryRightRun) {
        maxGain = Math.max(maxGain, segmentTree.query(queryLeftRun, queryRightRun));
      }

      maxGain = Math.max(maxGain, calculateBoundaryGain(runData, leftRun + 1, left, right));
      maxGain = Math.max(maxGain, calculateBoundaryGain(runData, rightRun - 1, left, right));

      answer.add(originalOnes + maxGain);
    }

    return answer;
  }

  private int calculateBoundaryGain(RunData runData, int oneRunIndex, int left, int right) {
    if (oneRunIndex <= 0 || oneRunIndex >= runData.runCount - 1) {
      return 0;
    }

    if (runData.runCharacters[oneRunIndex] != '1') {
      return 0;
    }

    if (runData.runCharacters[oneRunIndex - 1] != '0'
        || runData.runCharacters[oneRunIndex + 1] != '0') {
      return 0;
    }

    if (runData.runStart[oneRunIndex] < left || runData.runEnd[oneRunIndex] > right) {
      return 0;
    }

    if (left > runData.runEnd[oneRunIndex - 1] || right < runData.runStart[oneRunIndex + 1]) {
      return 0;
    }

    int leftZeroCount =
        runData.runStart[oneRunIndex] - Math.max(left, runData.runStart[oneRunIndex - 1]);

    int rightZeroCount =
        Math.min(right, runData.runEnd[oneRunIndex + 1]) - runData.runEnd[oneRunIndex];

    if (leftZeroCount <= 0 || rightZeroCount <= 0) {
      return 0;
    }

    return leftZeroCount + rightZeroCount;
  }

  private RunData buildRuns(String s) {
    int n = s.length();

    int[] tempStart = new int[n];
    int[] tempEnd = new int[n];
    int[] tempLength = new int[n];
    char[] tempCharacters = new char[n];
    int[] runIdByIndex = new int[n];

    int runCount = 0;
    int index = 0;

    while (index < n) {
      int start = index;
      char currentChar = s.charAt(index);

      while (index < n && s.charAt(index) == currentChar) {
        index++;
      }

      int end = index - 1;

      tempStart[runCount] = start;
      tempEnd[runCount] = end;
      tempLength[runCount] = end - start + 1;
      tempCharacters[runCount] = currentChar;

      for (int position = start; position <= end; position++) {
        runIdByIndex[position] = runCount;
      }

      runCount++;
    }

    int[] runStart = Arrays.copyOf(tempStart, runCount);
    int[] runEnd = Arrays.copyOf(tempEnd, runCount);
    int[] runLength = Arrays.copyOf(tempLength, runCount);
    char[] runCharacters = Arrays.copyOf(tempCharacters, runCount);

    int[] fullGain = new int[runCount];

    for (int runIndex = 1; runIndex + 1 < runCount; runIndex++) {
      if (runCharacters[runIndex] == '1'
          && runCharacters[runIndex - 1] == '0'
          && runCharacters[runIndex + 1] == '0') {
        fullGain[runIndex] = runLength[runIndex - 1] + runLength[runIndex + 1];
      }
    }

    return new RunData(
        runCount, runStart, runEnd, runLength, runCharacters, runIdByIndex, fullGain);
  }

  private static class RunData {
    int runCount;
    int[] runStart;
    int[] runEnd;
    int[] runLength;
    char[] runCharacters;
    int[] runIdByIndex;
    int[] fullGain;

    RunData(
        int runCount,
        int[] runStart,
        int[] runEnd,
        int[] runLength,
        char[] runCharacters,
        int[] runIdByIndex,
        int[] fullGain) {
      this.runCount = runCount;
      this.runStart = runStart;
      this.runEnd = runEnd;
      this.runLength = runLength;
      this.runCharacters = runCharacters;
      this.runIdByIndex = runIdByIndex;
      this.fullGain = fullGain;
    }
  }

  private static class SegmentTree {
    private final int size;
    private final int[] tree;

    SegmentTree(int[] values) {
      int n = values.length;
      int currentSize = 1;

      while (currentSize < n) {
        currentSize *= 2;
      }

      this.size = currentSize;
      this.tree = new int[size * 2];

      for (int i = 0; i < n; i++) {
        tree[size + i] = values[i];
      }

      for (int i = size - 1; i >= 1; i--) {
        tree[i] = Math.max(tree[i * 2], tree[i * 2 + 1]);
      }
    }

    int query(int left, int right) {
      left += size;
      right += size;

      int result = 0;

      while (left <= right) {
        if (left % 2 == 1) {
          result = Math.max(result, tree[left]);
          left++;
        }

        if (right % 2 == 0) {
          result = Math.max(result, tree[right]);
          right--;
        }

        left /= 2;
        right /= 2;
      }

      return result;
    }
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.maxActiveSectionsAfterTrade("01", new int[][] {{0, 1}}));
    // Output: [1]

    System.out.println(
        solution.maxActiveSectionsAfterTrade(
            "0100",
            new int[][] {
              {0, 3},
              {0, 2},
              {1, 3},
              {2, 3}
            }));
    // Output: [4, 3, 1, 1]

    System.out.println(
        solution.maxActiveSectionsAfterTrade(
            "1000100",
            new int[][] {
              {1, 5},
              {0, 6},
              {0, 4}
            }));
    // Output: [6, 7, 2]

    System.out.println(
        solution.maxActiveSectionsAfterTrade(
            "01010",
            new int[][] {
              {0, 3},
              {1, 4},
              {1, 3}
            }));
    // Output: [4, 4, 2]
  }
}
