package dev.vkh.solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {

  public static int maxBuilding(int n, int[][] restrictions) {
    List<int[]> buildingRestrictions = new ArrayList<>();

    buildingRestrictions.add(new int[] {1, 0});

    for (int[] restriction : restrictions) {
      buildingRestrictions.add(new int[] {restriction[0], restriction[1]});
    }

    buildingRestrictions.add(new int[] {n, n - 1});

    buildingRestrictions.sort(Comparator.comparingInt(restriction -> restriction[0]));

    for (int i = 1; i < buildingRestrictions.size(); i++) {
      int previousBuildingId = buildingRestrictions.get(i - 1)[0];
      int previousMaxHeight = buildingRestrictions.get(i - 1)[1];

      int currentBuildingId = buildingRestrictions.get(i)[0];
      int currentMaxHeight = buildingRestrictions.get(i)[1];

      int distance = currentBuildingId - previousBuildingId;
      int reachableMaxHeight = previousMaxHeight + distance;

      buildingRestrictions.get(i)[1] = Math.min(currentMaxHeight, reachableMaxHeight);
    }

    for (int i = buildingRestrictions.size() - 2; i >= 0; i--) {
      int nextBuildingId = buildingRestrictions.get(i + 1)[0];
      int nextMaxHeight = buildingRestrictions.get(i + 1)[1];

      int currentBuildingId = buildingRestrictions.get(i)[0];
      int currentMaxHeight = buildingRestrictions.get(i)[1];

      int distance = nextBuildingId - currentBuildingId;
      int reachableMaxHeight = nextMaxHeight + distance;

      buildingRestrictions.get(i)[1] = Math.min(currentMaxHeight, reachableMaxHeight);
    }

    int answer = 0;

    for (int i = 1; i < buildingRestrictions.size(); i++) {
      int leftBuildingId = buildingRestrictions.get(i - 1)[0];
      int leftMaxHeight = buildingRestrictions.get(i - 1)[1];

      int rightBuildingId = buildingRestrictions.get(i)[0];
      int rightMaxHeight = buildingRestrictions.get(i)[1];

      int distance = rightBuildingId - leftBuildingId;

      int highestPossibleBetweenThem = (leftMaxHeight + rightMaxHeight + distance) / 2;

      answer = Math.max(answer, highestPossibleBetweenThem);
    }

    return answer;
  }

  static void main() {
    int n1 = 5;
    int[][] restrictions1 = {
      {2, 1},
      {4, 1}
    };
    System.out.println(maxBuilding(n1, restrictions1)); // Output: 2

    int n2 = 6;
    int[][] restrictions2 = {};
    System.out.println(maxBuilding(n2, restrictions2)); // Output: 5

    int n3 = 10;
    int[][] restrictions3 = {
      {5, 3},
      {2, 5},
      {7, 4},
      {10, 3}
    };
    System.out.println(maxBuilding(n3, restrictions3)); // Output: 5
  }
}
