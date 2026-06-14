package dev.vkh.solutions;

class Solution {

  public int pairSum(ListNode head) {
    ListNode slowPointer = head;
    ListNode fastPointer = head;

    while (fastPointer != null && fastPointer.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
    }

    ListNode reversedSecondHalfHead = reverseLinkedList(slowPointer);

    int maximumTwinSum = 0;
    ListNode firstHalfPointer = head;
    ListNode secondHalfPointer = reversedSecondHalfHead;

    while (secondHalfPointer != null) {
      int currentTwinSum = firstHalfPointer.val + secondHalfPointer.val;
      maximumTwinSum = Math.max(maximumTwinSum, currentTwinSum);

      firstHalfPointer = firstHalfPointer.next;
      secondHalfPointer = secondHalfPointer.next;
    }

    return maximumTwinSum;
  }

  private ListNode reverseLinkedList(ListNode head) {
    ListNode previousNode = null;
    ListNode currentNode = head;

    while (currentNode != null) {
      ListNode nextNode = currentNode.next;
      currentNode.next = previousNode;
      previousNode = currentNode;
      currentNode = nextNode;
    }

    return previousNode;
  }

  private static class ListNode {
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

  static void main() {
    ListNode head = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));

    Solution solution = new Solution();
    System.out.println(solution.pairSum(head)); // 6
  }
}
