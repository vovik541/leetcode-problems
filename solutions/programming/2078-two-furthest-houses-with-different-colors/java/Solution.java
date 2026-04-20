package dev.vkh.solutions;

class Solution {
  public int maxDistance(int[] colors) {
    int maximumDistance = 0;

    for (int leftIndex = 0; leftIndex < colors.length; leftIndex++) {
      for (int rightIndex = colors.length - 1; rightIndex > leftIndex; rightIndex--) {
        if (colors[leftIndex] == colors[rightIndex]) {
          continue;
        }

        maximumDistance = Math.max(maximumDistance, rightIndex - leftIndex);
        break;
      }
    }

    return maximumDistance;
  }

  static void main() {
    System.out.println(new Solution().maxDistance(new int[] {9, 9, 9, 18, 9, 9, 9, 9, 9, 18}));
  }
}
