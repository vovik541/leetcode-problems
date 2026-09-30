package dev.vkh.solutions;

class Solution {

  public static int[] maxDepthAfterSplit(String seq) {
    int[] answer = new int[seq.length()];
    int depth = 0;

    for (int i = 0; i < seq.length(); i++) {
      if (seq.charAt(i) == '(') {
        depth++;
        answer[i] = depth % 2;
      } else {
        answer[i] = depth % 2;
        depth--;
      }
    }

    return answer;
  }

  static void main() {
    print(maxDepthAfterSplit("(()())")); // [0, 1, 1, 1, 1, 0]
    print(maxDepthAfterSplit("()(())()")); // [0, 0, 0, 1, 1, 0, 1, 1]
  }

  static void print(int[] answer) {
    for (int value : answer) {
      System.out.print(value + " ");
    }
    System.out.println();
  }
}
