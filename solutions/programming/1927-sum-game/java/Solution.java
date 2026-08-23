package dev.vkh.solutions;

class Solution {

  public static boolean sumGame(String num) {
    int halfLength = num.length() / 2;

    int leftSum = 0;
    int rightSum = 0;
    int leftQuestionMarks = 0;
    int rightQuestionMarks = 0;

    for (int index = 0; index < num.length(); index++) {
      char currentCharacter = num.charAt(index);

      if (currentCharacter == '?') {
        if (index < halfLength) {
          leftQuestionMarks++;
        } else {
          rightQuestionMarks++;
        }
      } else {
        int digitValue = currentCharacter - '0';

        if (index < halfLength) {
          leftSum += digitValue;
        } else {
          rightSum += digitValue;
        }
      }
    }

    int totalQuestionMarks = leftQuestionMarks + rightQuestionMarks;

    if ((totalQuestionMarks & 1) == 1) {
      return true;
    }

    int sumDifference = leftSum - rightSum;
    int questionMarkDifference = rightQuestionMarks - leftQuestionMarks;

    return sumDifference * 2 != questionMarkDifference * 9;
  }

  static void main() {
    System.out.println(sumGame("5023")); // false
    System.out.println(sumGame("25??")); // true
    System.out.println(sumGame("?3295???")); // false
  }
}
