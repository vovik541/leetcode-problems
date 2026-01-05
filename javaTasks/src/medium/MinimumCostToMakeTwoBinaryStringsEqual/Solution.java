package medium.MinimumCostToMakeTwoBinaryStringsEqual;

class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minimumCost("01000", "10111", 10, 2, 2));
    }

    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        long count01 = 0; // s[i]=0, t[i]=1
        long count10 = 0; // s[i]=1, t[i]=0

        for (int index = 0; index < s.length(); index++) {
            char sChar = s.charAt(index);
            char tChar = t.charAt(index);

            if (sChar == tChar) {
                continue;
            }
            if (sChar == '0') {
                count01++;
            } else {
                count10++;
            }
        }

        long totalMismatches = count01 + count10;
        if (totalMismatches == 0) {
            return 0L;
        }

        long flip = flipCost;
        long swap = swapCost;
        long cross = crossCost;

        // If swapping is not better than flipping two mismatches, never use swap/cross
        if (swap >= 2L * flip) {
            return totalMismatches * flip;
        }

        long pairedMismatches = Math.min(count01, count10);
        long remainingUnpaired = Math.abs(count01 - count10);

        //use swap for each (01+10) pair, flip the rest
        long baseCost = pairedMismatches * swap + remainingUnpaired * flip;

        // Each cross+swap can replace 2 flips for the unpaired mismatches
        // delta = (cross + swap) - 2*flip
        long replaceTwoFlipsDelta = (cross + swap) - 2L * flip;

        if (replaceTwoFlipsDelta < 0) {
            long maxReplacements = remainingUnpaired / 2; // each replacement reduces unpaired by 2
            baseCost += maxReplacements * replaceTwoFlipsDelta;
        }

        return baseCost;
    }
}
