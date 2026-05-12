package dev.vkh.solutions;

class Solution {
  public int[] separateDigits(int[] nums) {
    StringBuilder sb = new StringBuilder();
    for (int num : nums) {
      sb.append(num);
    }
    String allDigitsString = sb.toString();
    int[] answer = new int[allDigitsString.length()];
    for (int i = 0; i < allDigitsString.length(); i++) {
      answer[i] = allDigitsString.charAt(i) - '0';
    }

    return answer;
  }

  static void main() {
    for (int digit : new Solution().separateDigits(new int[] {13, 25, 83, 77})) {
      System.out.println(digit);
    }
  }
}
