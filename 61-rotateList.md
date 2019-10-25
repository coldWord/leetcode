## 题目

 给定一个链表，旋转链表，将链表每个节点向右移动 *k* 个位置，其中 *k* 是非负数。 

## 思路

找到第k+1个节点的位置，把后半截链表加到最前面。(k>length时，取k=k%length)

## Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        head = dummy.next;
        int count = length - (k % length);
        for (int i = 0; i < count-1; ++i) head = head.next;
        ListNode move = head.next;
        head.next = null;
        ListNode pHead = dummy.next;
        dummy.next = move;
        while (move != null && move.next != null) 
            move = move.next;
        if (move == null)
            dummy.next = pHead;
        else
            move.next = pHead;
        return dummy.next;
    }
}
```

