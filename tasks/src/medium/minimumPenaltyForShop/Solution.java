package medium.minimumPenaltyForShop;

class Solution {

    public static void main(String[] args) {
        System.out.println(bestClosingTime("YYYY"));
    }

    public static int bestClosingTime(String customers) {
        int n = customers.length();

        int totalY = 0;
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') totalY++;
        }

        int penalty = totalY;
        int bestJ = 0;
        int bestPenalty = penalty;

        for (int i = 0; i < n; i++) {
            char c = customers.charAt(i);

            if (c == 'Y') penalty -= 1;
            else penalty += 1;

            int j = i + 1;
            if (penalty < bestPenalty) {
                bestPenalty = penalty;
                bestJ = j;
            }
        }

        return bestJ;
    }
}