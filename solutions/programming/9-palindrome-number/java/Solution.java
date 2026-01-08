package dev.vkh.solutions;

public class Solution {
  public boolean isPalindrome(int x) {

    if (x < 0 || (x % 10 == 0 && x != 0)) {
      return false;
    }

    int reversedHalf = 0;

    while (x > reversedHalf) {
      int lastDigit = x % 10;
      reversedHalf = reversedHalf * 10 + lastDigit;
      x /= 10;
    }

    return x == reversedHalf || x == reversedHalf / 10;
  }

  static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isPalindrome(121));
    System.out.println(solution.isPalindrome(-121));
    System.out.println(solution.isPalindrome(10));
  }
}
