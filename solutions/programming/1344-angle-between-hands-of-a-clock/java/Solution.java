package dev.vkh.solutions;

class Solution {
  public double angleClock(int hour, int minutes) {
    double fromMinutesAngle = minutes * 6;
    double fromHoursAngle = hour * 30 + (minutes / 60.0) * 30;

    return Math.min(
        Math.abs(fromMinutesAngle - fromHoursAngle),
        360 - Math.abs(fromMinutesAngle - fromHoursAngle));
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.angleClock(12, 30)); // 165
    System.out.println(solution.angleClock(3, 30)); // 75
    System.out.println(solution.angleClock(3, 15)); // 7.5
  }
}
