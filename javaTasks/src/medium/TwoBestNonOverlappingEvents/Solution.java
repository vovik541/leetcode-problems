package medium.TwoBestNonOverlappingEvents;

import java.util.Arrays;
import java.util.Comparator;

class Solution {

    public static void main(String[] args) {
        System.out.println(maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {2, 4, 3}}));
    }

    public static int maxTwoEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(event -> event[0]));

        int eventsCount = events.length;

        int[] startTimes = new int[eventsCount];
        for (int i = 0; i < eventsCount; i++) {
            startTimes[i] = events[i][0];
        }

        int[] suffixMaxValue = new int[eventsCount + 1];
        for (int i = eventsCount - 1; i >= 0; i--) {
            suffixMaxValue[i] = Math.max(suffixMaxValue[i + 1], events[i][2]);
        }

        int maxTotalValue = 0;

        for (int i = 0; i < eventsCount; i++) {
            int endTime = events[i][1];
            int value = events[i][2];

            if (value > maxTotalValue) {
                maxTotalValue = value;
            }

            int nextEventIndex = firstEventWithStartAtOrAfter(startTimes, endTime + 1);
            int valueWithNextEvent = value + suffixMaxValue[nextEventIndex];

            if (valueWithNextEvent > maxTotalValue) {
                maxTotalValue = valueWithNextEvent;
            }
        }

        return maxTotalValue;
    }

    private static int firstEventWithStartAtOrAfter(int[] startTimes, int targetStartTime) {
        int left = 0, right = startTimes.length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (startTimes[mid] < targetStartTime) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
