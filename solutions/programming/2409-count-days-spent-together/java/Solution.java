package dev.vkh.solutions;

class Solution {

  private static final int[] DAYS_IN_MONTH = {
    31, 28, 31, 30, 31, 30,
    31, 31, 30, 31, 30, 31
  };

  public static int countDaysTogether(
      String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {

    int start = Math.max(dayOfYear(arriveAlice), dayOfYear(arriveBob));

    int end = Math.min(dayOfYear(leaveAlice), dayOfYear(leaveBob));

    return Math.max(0, end - start + 1);
  }

  private static int dayOfYear(String date) {
    int month = Integer.parseInt(date.substring(0, 2));
    int day = Integer.parseInt(date.substring(3, 5));

    for (int i = 0; i < month - 1; i++) {
      day += DAYS_IN_MONTH[i];
    }

    return day;
  }

  static void main() {
    System.out.println(countDaysTogether("08-15", "08-18", "08-16", "08-19")); // 3
    System.out.println(countDaysTogether("10-01", "10-31", "11-01", "12-31")); // 0
    System.out.println(countDaysTogether("01-01", "01-10", "01-05", "01-15")); // 6
    System.out.println(countDaysTogether("02-28", "03-01", "03-01", "03-01")); // 1
    System.out.println(countDaysTogether("12-25", "12-31", "12-25", "12-31")); // 7
  }
}
