package dev.vkh.solutions;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
    Map<Integer, Integer> rowToReservedMask = new HashMap<>();

    for (int[] reservedSeat : reservedSeats) {
      int row = reservedSeat[0];
      int seat = reservedSeat[1];

      rowToReservedMask.put(row, rowToReservedMask.getOrDefault(row, 0) | (1 << seat));
    }

    int rowsWithoutReservations = n - rowToReservedMask.size();
    int totalFamilies = rowsWithoutReservations * 2;

    for (int reservedMask : rowToReservedMask.values()) {
      boolean leftBlockAvailable = isBlockAvailable(reservedMask, 2, 5);
      boolean middleBlockAvailable = isBlockAvailable(reservedMask, 4, 7);
      boolean rightBlockAvailable = isBlockAvailable(reservedMask, 6, 9);

      if (leftBlockAvailable && rightBlockAvailable) {
        totalFamilies += 2;
      } else if (leftBlockAvailable || middleBlockAvailable || rightBlockAvailable) {
        totalFamilies += 1;
      }
    }

    return totalFamilies;
  }

  private static boolean isBlockAvailable(int reservedMask, int startSeat, int endSeat) {
    for (int seat = startSeat; seat <= endSeat; seat++) {
      if ((reservedMask & (1 << seat)) != 0) {
        return false;
      }
    }

    return true;
  }

  static void main() {
    System.out.println(
        maxNumberOfFamilies(
            3,
            new int[][] {
              {1, 2},
              {1, 3},
              {1, 8},
              {2, 6},
              {3, 1},
              {3, 10}
            })); // 4
    System.out.println(
        maxNumberOfFamilies(
            2,
            new int[][] {
              {2, 1},
              {1, 8},
              {2, 6}
            })); // 2
    System.out.println(
        maxNumberOfFamilies(
            4,
            new int[][] {
              {4, 3},
              {1, 4},
              {4, 6},
              {1, 7}
            })); // 4
  }
}
