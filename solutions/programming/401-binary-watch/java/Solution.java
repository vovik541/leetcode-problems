package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<String> readBinaryWatch(int turnedOn) {
    List<String> possibleTimes = new ArrayList<>();

    for (int hourValue = 0; hourValue < 12; hourValue++) {
      for (int minuteValue = 0; minuteValue < 60; minuteValue++) {
        int totalEnabledLeds = Integer.bitCount(hourValue) + Integer.bitCount(minuteValue);
        if (totalEnabledLeds == turnedOn) {
          possibleTimes.add(formatTime(hourValue, minuteValue));
        }
      }
    }

    return possibleTimes;
  }

  private String formatTime(int hourValue, int minuteValue) {
    return hourValue + ":" + (minuteValue < 10 ? "0" : "") + minuteValue;
  }

  static void main() {
    Solution s = new Solution();
    System.out.println(s.readBinaryWatch(1));
  }
}
