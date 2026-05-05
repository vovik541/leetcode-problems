package dev.vkh.solutions;

class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null) {
      return head;
    }

    int totalNodes = 1;

    ListNode tail = head;
    while (tail.next != null) {
      totalNodes++;
      tail = tail.next;
    }

    k = k % totalNodes;

    if (k == 0) {
      return head;
    }

    ListNode newHead;
    ListNode prev = head;
    ListNode current = head;

    while (totalNodes - k > 0) {
      prev = current;
      current = current.next;
      totalNodes--;
    }

    newHead = current;
    prev.next = null;

    tail = newHead;
    while (tail.next != null) {
      tail = tail.next;
    }

    tail.next = head;

    return newHead;
  }

  static void main() {
    ListNode head = new ListNode(1);
    ListNode current = head;
    for (int i = 2; i < 6; i++) {
      current.next = new ListNode(i);
      current = current.next;
    }

    ListNode result = new Solution().rotateRight(head, 2);

    while (result != null) {
      System.out.println(result.val);
      result = result.next;
    }
  }

  public static class ListNode {
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
