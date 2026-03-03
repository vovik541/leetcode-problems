package dev.vkh.solutions;

class Solution {

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.findKthBit(3, 1)); // 0
    System.out.println(solution.findKthBit(4, 11)); // 1
    System.out.println(solution.findKthBit(1, 1)); // 0
  }

  public char findKthBit(int n, int k) {
    boolean isInverted = false;
    int currentLength = (1 << n) - 1;

    while (k > 1) {
      int middleIndex = (currentLength / 2) + 1;

      if (k == middleIndex) {
        char middleBit = '1';
        return isInverted ? invertBit(middleBit) : middleBit;
      }

      if (k > middleIndex) {
        k = currentLength - k + 1;
        isInverted = !isInverted;
      }

      currentLength = (currentLength - 1) / 2;
    }

    char baseBit = '0';
    return isInverted ? invertBit(baseBit) : baseBit;
  }

  private char invertBit(char bit) {
    return bit == '0' ? '1' : '0';
  }
}
