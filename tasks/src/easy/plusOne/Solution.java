package easy.plusOne;

import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().plusOne(new int[]{1, 2, 3})));
    }

    public int[] plusOne(int[] digits) {
        int numberOfDigits = digits.length;

        for (int digitIndex = numberOfDigits - 1; digitIndex >= 0; digitIndex--) {

            if (digits[digitIndex] < 9) {
                digits[digitIndex]++;
                return digits;
            }

            digits[digitIndex] = 0;
        }

        int[] resultWithExtraDigit = new int[numberOfDigits + 1];
        resultWithExtraDigit[0] = 1;

        return resultWithExtraDigit;
    }
}
