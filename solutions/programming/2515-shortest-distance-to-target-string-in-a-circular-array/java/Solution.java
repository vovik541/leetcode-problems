package dev.vkh.solutions;

class Solution {
  public int closestTarget(String[] words, String target, int startIndex) {
    int arrayLength = words.length;
    int minimumDistance = Integer.MAX_VALUE;

    for (int currentIndex = 0; currentIndex < arrayLength; currentIndex++) {
      if (!words[currentIndex].equals(target)) {
        continue;
      }

      int directDistance = Math.abs(currentIndex - startIndex);
      int circularDistance = arrayLength - directDistance;

      minimumDistance = Math.min(minimumDistance, Math.min(directDistance, circularDistance));
    }

    return minimumDistance == Integer.MAX_VALUE ? -1 : minimumDistance;
  }

  static void main() {
    System.out.println(
        new Solution()
            .closetTarget(new String[] {"hello", "i", "am", "leetcode", "hello"}, "hello", 1));
  }
}
