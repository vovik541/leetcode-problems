package dev.vkh.solutions.medium.fourDivisors;

class Solution {

  static void main(String[] args) {
    int[] nums = {21, 4, 7};

    System.out.println(new Solution().sumFourDivisors(nums));
  }

  // A number has exactly four divisors only if it is either the cube of a prime number or
  // the product of two distinct prime numbers no other numbers can have exactly four divisors
  public int sumFourDivisors(int[] nums) {
    int totalDivisorSum = 0;

    for (int value : nums) {
      totalDivisorSum += sumIfExactlyFourDivisors(value);
    }

    return totalDivisorSum;
  }

  private int sumIfExactlyFourDivisors(int value) {
    int divisorCount = 0;
    int divisorSum = 0;

    int limit = (int) Math.sqrt(value);

    for (int candidateDivisor = 1; candidateDivisor <= limit; candidateDivisor++) {
      if (value % candidateDivisor != 0) continue;

      int pairedDivisor = value / candidateDivisor;

      divisorCount++;
      divisorSum += candidateDivisor;

      if (pairedDivisor != candidateDivisor) {
        divisorCount++;
        divisorSum += pairedDivisor;
      }

      if (divisorCount > 4) return 0;
    }

    return divisorCount == 4 ? divisorSum : 0;
  }
}
