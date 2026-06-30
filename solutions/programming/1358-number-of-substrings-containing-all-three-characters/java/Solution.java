package dev.vkh.solutions;

public class Solution {

  public int numberOfSubstrings(String s) {
    int[] characterCount = new int[3];

    int left = 0;
    int validSubstrings = 0;

    for (int right = 0; right < s.length(); right++) {
      characterCount[s.charAt(right) - 'a']++;

      while (characterCount[0] > 0 && characterCount[1] > 0 && characterCount[2] > 0) {

        validSubstrings += s.length() - right;

        characterCount[s.charAt(left) - 'a']--;
        left++;
      }
    }

    return validSubstrings;
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(solution.numberOfSubstrings("abcabc")); // 10
    System.out.println(solution.numberOfSubstrings("aaacb")); // 3
  }
}
