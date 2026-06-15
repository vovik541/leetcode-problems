package dev.vkh.solutions;

class Solution {

  public ListNode deleteMiddle(ListNode head) {
    if (head.next == null) {
      return null;
    }

    ListNode slowPointer = head;
    ListNode fastPointer = head;
    ListNode previousNode = null;

    while (fastPointer != null && fastPointer.next != null) {
      previousNode = slowPointer;

      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
    }

    previousNode.next = slowPointer.next;

    return head;
  }

  private class ListNode {
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

  void main() {
    Solution solution = new Solution();

    ListNode head =
        new ListNode(
            1,
            new ListNode(
                3,
                new ListNode(
                    4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6)))))));
    ListNode result = solution.deleteMiddle(head);

    while (result != null) {
      System.out.print(result.val + " ");
      result = result.next;
    }

    // 1 3 4 1 2 6
  }
}
