package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static String[] createGrid(int m, int n) {
    String[] result = new String[m];

    for (int row = 0; row < m; row++) {
      char[] cells = new char[n];
      Arrays.fill(cells, '#');

      if (row == 0) {
        Arrays.fill(cells, '.');
      } else {
        cells[n - 1] = '.';
      }

      result[row] = new String(cells);
    }

    return result;
  }

  static void main() {
    System.out.println(Arrays.toString(createGrid(2, 3))); // [..., ##.]
    System.out.println(Arrays.toString(createGrid(3, 3))); // [..., ##., ##.]
    System.out.println(Arrays.toString(createGrid(1, 4))); // [....]
    System.out.println(Arrays.toString(createGrid(4, 1))); // [., ., ., .]
    System.out.println(Arrays.toString(createGrid(2, 2))); // [.., #.]
  }
}
