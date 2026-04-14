package dev.vkh.solutions;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {
  public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
    int robotCount = robot.size();
    int factoryCount = factory.length;

    Collections.sort(robot);
    Arrays.sort(
        factory,
        (firstFactory, secondFactory) -> Integer.compare(firstFactory[0], secondFactory[0]));

    long[][] dynamicProgramming = new long[robotCount + 1][factoryCount + 1];
    long impossibleValue = Long.MAX_VALUE / 4;

    for (int robotIndex = 0; robotIndex <= robotCount; robotIndex++) {
      Arrays.fill(dynamicProgramming[robotIndex], impossibleValue);
    }

    dynamicProgramming[0][0] = 0;

    for (int factoryIndex = 1; factoryIndex <= factoryCount; factoryIndex++) {
      int factoryPosition = factory[factoryIndex - 1][0];
      int factoryLimit = factory[factoryIndex - 1][1];

      dynamicProgramming[0][factoryIndex] = 0;

      for (int repairedRobotCount = 1; repairedRobotCount <= robotCount; repairedRobotCount++) {
        dynamicProgramming[repairedRobotCount][factoryIndex] =
            dynamicProgramming[repairedRobotCount][factoryIndex - 1];

        long distanceSum = 0;

        for (int assignedRobotCount = 1;
            assignedRobotCount <= factoryLimit && assignedRobotCount <= repairedRobotCount;
            assignedRobotCount++) {

          int robotPosition = robot.get(repairedRobotCount - assignedRobotCount);
          distanceSum += Math.abs((long) robotPosition - factoryPosition);

          dynamicProgramming[repairedRobotCount][factoryIndex] =
              Math.min(
                  dynamicProgramming[repairedRobotCount][factoryIndex],
                  dynamicProgramming[repairedRobotCount - assignedRobotCount][factoryIndex - 1]
                      + distanceSum);
        }
      }
    }

    return dynamicProgramming[robotCount][factoryCount];
  }

  static void main() {
    System.out.println(
        new Solution()
            .minimumTotalDistance(
                new LinkedList<>() {
                  {
                    this.add(0);
                    this.add(4);
                    this.add(6);
                  }
                },
                new int[][] {{2, 2}, {6, 2}}));
  }
}
