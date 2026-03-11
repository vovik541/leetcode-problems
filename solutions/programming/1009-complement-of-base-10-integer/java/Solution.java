package dev.vkh.solutions;

class Solution {

  public int bitwiseComplement(int n) {
    if (n == 0) {
      return 1;
    }

    int bitMask = 0;
    int temporaryValue = n;

    while (temporaryValue > 0) {
      bitMask = (bitMask << 1) | 1;
      temporaryValue >>= 1;
    }

    return bitMask ^ n;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.bitwiseComplement(5));
    System.out.println(solution.bitwiseComplement(7));
  }
}
