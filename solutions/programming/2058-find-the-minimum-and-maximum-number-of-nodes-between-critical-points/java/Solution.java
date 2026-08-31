package dev.vkh.solutions;

import java.util.Arrays;

class Solution {

  public static int[] nodesBetweenCriticalPoints(ListNode head) {
    int firstCriticalIndex = -1;
    int previousCriticalIndex = -1;
    int minimumDistance = Integer.MAX_VALUE;

    ListNode previousNode = head;
    ListNode currentNode = head.next;

    int currentIndex = 1;

    while (currentNode != null && currentNode.next != null) {
      ListNode nextNode = currentNode.next;

      boolean isLocalMaximum = currentNode.val > previousNode.val && currentNode.val > nextNode.val;

      boolean isLocalMinimum = currentNode.val < previousNode.val && currentNode.val < nextNode.val;

      if (isLocalMaximum || isLocalMinimum) {
        if (firstCriticalIndex == -1) {
          firstCriticalIndex = currentIndex;
        }

        if (previousCriticalIndex != -1) {
          minimumDistance = Math.min(minimumDistance, currentIndex - previousCriticalIndex);
        }

        previousCriticalIndex = currentIndex;
      }

      previousNode = currentNode;
      currentNode = nextNode;
      currentIndex++;
    }

    if (firstCriticalIndex == previousCriticalIndex) {
      return new int[] {-1, -1};
    }

    int maximumDistance = previousCriticalIndex - firstCriticalIndex;

    return new int[] {minimumDistance, maximumDistance};
  }

  static void main() {
    System.out.println(
        Arrays.toString(nodesBetweenCriticalPoints(buildList(new int[] {3, 1})))); // [-1, -1]

    System.out.println(
        Arrays.toString(
            nodesBetweenCriticalPoints(buildList(new int[] {5, 3, 1, 2, 5, 1, 2})))); // [1, 3]

    System.out.println(
        Arrays.toString(
            nodesBetweenCriticalPoints(
                buildList(new int[] {1, 3, 2, 2, 3, 2, 2, 2, 7})))); // [3, 3]
  }

  private static ListNode buildList(int[] values) {
    ListNode dummyHead = new ListNode();
    ListNode currentNode = dummyHead;

    for (int value : values) {
      currentNode.next = new ListNode(value);
      currentNode = currentNode.next;
    }

    return dummyHead.next;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
