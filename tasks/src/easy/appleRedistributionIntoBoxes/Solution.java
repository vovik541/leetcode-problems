package easy.appleRedistributionIntoBoxes;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        System.out.println(minimumBoxes(new int[]{1, 3, 2}, new int[]{4, 3, 1, 5, 2}));
    }

    public static int minimumBoxes(int[] apple, int[] capacity) {
        int apples = Arrays.stream(apple).sum();
        Arrays.sort(capacity);

        int boxIndex = capacity.length;
        int totalUsedCapacity = 0;
        while (totalUsedCapacity < apples) {
            --boxIndex;
            totalUsedCapacity += capacity[boxIndex];
        }

        return capacity.length - boxIndex;
    }
}
