package dev.vkh.solutions;

import java.util.*;

class Solution {

  public int maximumAmount(int[][] coins) {
    int rowCount = coins.length;
    int columnCount = coins[0].length;

    int[][][] maximumProfit = new int[rowCount][columnCount][3];
    int negativeInfinity = Integer.MIN_VALUE / 4;

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        for (int usedNeutralizationsCount = 0;
            usedNeutralizationsCount < 3;
            usedNeutralizationsCount++) {
          maximumProfit[rowIndex][columnIndex][usedNeutralizationsCount] = negativeInfinity;
        }
      }
    }

    int startCellValue = coins[0][0];
    maximumProfit[0][0][0] = startCellValue;
    if (startCellValue < 0) {
      maximumProfit[0][0][1] = 0;
    }

    for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
      for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
        if (rowIndex == 0 && columnIndex == 0) {
          continue;
        }

        int currentCellValue = coins[rowIndex][columnIndex];

        for (int usedNeutralizationsCount = 0;
            usedNeutralizationsCount < 3;
            usedNeutralizationsCount++) {
          int bestPreviousProfit = negativeInfinity;

          if (rowIndex > 0) {
            bestPreviousProfit =
                Math.max(
                    bestPreviousProfit,
                    maximumProfit[rowIndex - 1][columnIndex][usedNeutralizationsCount]);
          }

          if (columnIndex > 0) {
            bestPreviousProfit =
                Math.max(
                    bestPreviousProfit,
                    maximumProfit[rowIndex][columnIndex - 1][usedNeutralizationsCount]);
          }

          if (bestPreviousProfit != negativeInfinity) {
            maximumProfit[rowIndex][columnIndex][usedNeutralizationsCount] =
                Math.max(
                    maximumProfit[rowIndex][columnIndex][usedNeutralizationsCount],
                    bestPreviousProfit + currentCellValue);
          }

          if (currentCellValue < 0 && usedNeutralizationsCount > 0) {
            int bestPreviousProfitWithOneLessNeutralization = negativeInfinity;

            if (rowIndex > 0) {
              bestPreviousProfitWithOneLessNeutralization =
                  Math.max(
                      bestPreviousProfitWithOneLessNeutralization,
                      maximumProfit[rowIndex - 1][columnIndex][usedNeutralizationsCount - 1]);
            }

            if (columnIndex > 0) {
              bestPreviousProfitWithOneLessNeutralization =
                  Math.max(
                      bestPreviousProfitWithOneLessNeutralization,
                      maximumProfit[rowIndex][columnIndex - 1][usedNeutralizationsCount - 1]);
            }

            if (bestPreviousProfitWithOneLessNeutralization != negativeInfinity) {
              maximumProfit[rowIndex][columnIndex][usedNeutralizationsCount] =
                  Math.max(
                      maximumProfit[rowIndex][columnIndex][usedNeutralizationsCount],
                      bestPreviousProfitWithOneLessNeutralization);
            }
          }
        }
      }
    }

    int lastRowIndex = rowCount - 1;
    int lastColumnIndex = columnCount - 1;

    return Math.max(
        maximumProfit[lastRowIndex][lastColumnIndex][0],
        Math.max(
            maximumProfit[lastRowIndex][lastColumnIndex][1],
            maximumProfit[lastRowIndex][lastColumnIndex][2]));
  }

  static void main() {
    Solution solution = new Solution();

    int[][] coins1 = {
            {0, 1, -1},
            {1, -2, 3},
            {2, -3, 4}
    };
    System.out.println(solution.maximumAmount(coins1)); // 8

    int[][] coins2 = {
            {10, 10, 10},
            {10, 10, 10}
    };
    System.out.println(solution.maximumAmount(coins2)); // 40
  }
}
