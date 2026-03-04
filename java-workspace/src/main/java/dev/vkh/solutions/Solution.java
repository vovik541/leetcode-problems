package dev.vkh.solutions;

class Solution {

  public int numSpecial(int[][] mat) {
    boolean notSpottedInRaw;
    boolean notSpottedInCol;

    int ans = 0;

    nextRaw:
    for (int i = 0; i < mat.length; i++) {
      notSpottedInRaw = true;
      notSpottedInCol = true;
      for (int j = 0; j < mat[i].length; j++) {
        if (mat[i][j] == 1) {
          if (notSpottedInRaw) {
            for (int h = 0; h < mat.length; h++) {
              if (mat[h][j] == 1) {
                if (notSpottedInCol) {
                  notSpottedInCol = false;
                } else {
                  continue nextRaw;
                }
              }
            }
            notSpottedInRaw = false;
          } else {
            continue nextRaw;
          }
        }
      }
      ans += 1;
    }

    return ans;
  }

  static void main() {
    System.out.println(new Solution().numSpecial(new int[][] {{1, 0, 0}, {0, 0, 1}, {1, 0, 0}}));
  }
}
