package medium.maximizeHappinessOfSelectedChildren;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        System.out.println(maximumHappinessSum1(new int[]{1,2,3}, 2));
//        System.out.println(maximumHappinessSum2(new int[]{1,2,3}, 2));
    }

    //85 ms Runtime
    public static long maximumHappinessSum1(int[] happiness, int k) {
        PriorityQueue<Integer> pq =
                new PriorityQueue<>((a, b) -> b - a);

        for (int h : happiness) {
            pq.add(h);
        }

        long sum = 0;
        int dec = 0;

        while (k-- > 0 && !pq.isEmpty()) {
            int cur = pq.poll() - dec;
            if (cur <= 0) break;
            sum += cur;
            dec++;
        }

        return sum;
    }

    //44 ms Runtime
    public static long maximumHappinessSum2(int[] happiness, int k) {
        Arrays.sort(happiness);
        long sum = 0;
        int dec = 0;

        for (int i = happiness.length - 1; i >= 0 && k > 0; i--, k--) {
            int gain = happiness[i] - dec;
            if (gain <= 0) break;
            sum += gain;
            dec++;
        }
        return sum;
    }
}