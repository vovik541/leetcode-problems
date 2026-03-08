package dev.vkh.solutions;

class Solution {

  public String findDifferentBinaryString(String[] nums) {
    int n = nums.length;
    char[] differentBinaryString = new char[n];

    for (int index = 0; index < n; index++) {
      differentBinaryString[index] = nums[index].charAt(index) == '0' ? '1' : '0';
    }

    return new String(differentBinaryString);
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
        solution.findDifferentBinaryString(new String[] {"01", "10"}));
    System.out.println(
        solution.findDifferentBinaryString(new String[] {"00", "01"}));
    System.out.println(
        solution.findDifferentBinaryString(new String[] {"111", "011", "001"}));
  }
}
