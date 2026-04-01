package dev.vkh.solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

class Solution {
  public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
    int robotCount = positions.length;
    Robot[] robotsSortedByPosition = new Robot[robotCount];

    for (int robotIndex = 0; robotIndex < robotCount; robotIndex++) {
      robotsSortedByPosition[robotIndex] =
          new Robot(
              positions[robotIndex],
              healths[robotIndex],
              directions.charAt(robotIndex),
              robotIndex);
    }

    Arrays.sort(robotsSortedByPosition, Comparator.comparingInt(robot -> robot.position));

    Deque<Robot> rightMovingRobotsStack = new ArrayDeque<>();

    for (Robot currentRobot : robotsSortedByPosition) {
      if (currentRobot.direction == 'R') {
        rightMovingRobotsStack.push(currentRobot);
        continue;
      }

      while (!rightMovingRobotsStack.isEmpty() && currentRobot.health > 0) {
        Robot rightMovingRobot = rightMovingRobotsStack.peek();

        if (rightMovingRobot.health < currentRobot.health) {
          rightMovingRobotsStack.pop();
          currentRobot.health--;
          rightMovingRobot.health = 0;
        } else if (rightMovingRobot.health == currentRobot.health) {
          rightMovingRobotsStack.pop();
          rightMovingRobot.health = 0;
          currentRobot.health = 0;
        } else {
          rightMovingRobot.health--;
          currentRobot.health = 0;
        }
      }
    }

    List<Robot> survivingRobots = new ArrayList<>();
    for (Robot robot : robotsSortedByPosition) {
      if (robot.health > 0) {
        survivingRobots.add(robot);
      }
    }

    survivingRobots.sort(Comparator.comparingInt(robot -> robot.originalIndex));

    List<Integer> survivingHealths = new ArrayList<>();
    for (Robot robot : survivingRobots) {
      survivingHealths.add(robot.health);
    }

    return survivingHealths;
  }

  private static class Robot {
    private final int position;
    private int health;
    private final char direction;
    private final int originalIndex;

    private Robot(int position, int health, char direction, int originalIndex) {
      this.position = position;
      this.health = health;
      this.direction = direction;
      this.originalIndex = originalIndex;
    }
  }

  static void main() {
    Solution solution = new Solution();

    System.out.println(
            Arrays.toString(
                    new List[]{solution.survivedRobotsHealths(
                            new int[]{5, 4, 3, 2, 1},
                            new int[]{2, 17, 9, 15, 10},
                            "RRRRR")})); // [2, 17, 9, 15, 10]
  }
}
