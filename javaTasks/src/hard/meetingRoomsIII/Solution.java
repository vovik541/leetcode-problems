package hard.meetingRoomsIII;

import java.util.*;

class Solution {

    public static void main(String[] args) {
        int[][] meetings = {
                {0, 10},
                {1, 5},
                {2, 7},
                {3, 4}
        };

        System.out.println(mostBooked(2, meetings));
    }

    public static int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) availableRooms.add(i);

        PriorityQueue<long[]> busyRooms = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        );

        int[] meetingCountByRoom = new int[n];

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                long[] finished = busyRooms.poll();
                availableRooms.add((int) finished[1]);
            }

            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll();
                meetingCountByRoom[room]++;
                busyRooms.add(new long[]{end, room});
            } else {
                long[] earliest = busyRooms.poll();
                long earliestEnd = earliest[0];
                int room = (int) earliest[1];

                meetingCountByRoom[room]++;
                long newEnd = earliestEnd + duration;
                busyRooms.add(new long[]{newEnd, room});
            }
        }

        int bestRoom = 0;
        for (int i = 1; i < n; i++) {
            if (meetingCountByRoom[i] > meetingCountByRoom[bestRoom]) {
                bestRoom = i;
            }
        }

        return bestRoom;
    }
}