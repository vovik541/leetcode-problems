package dev.vkh.solutions;

class Solution {
  public char nextGreatestLetter(char[] letters, char target) {
    int left = 0;
    int right = letters.length - 1;

    while (left <= right) {
      int middleIndex = left + (right - left) / 2;

      if (letters[middleIndex] <= target) {
        left = middleIndex + 1;
      } else {
        right = middleIndex - 1;
      }
    }

    return letters[left % letters.length];
  }

  static void main() {
    System.out.println(new Solution().nextGreatestLetter(new char[] {'c', 'f', 'j'}, 'a'));
  }
}
