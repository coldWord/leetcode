## Problem

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

## Idea

设置多个指针分别指向奇数链表的尾，偶数链表的头和尾.

## Solution(second)
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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode oddTail = head, evenHead, evenTail;
        evenHead = evenTail = head.next;
        ListNode cur = head.next.next;
        boolean isOdd = true;
        while (cur != null) {
            ListNode curNext = cur.next;
            if (isOdd) {
                oddTail.next = cur;
                cur.next = evenHead;
                evenTail.next = curNext;
                oddTail = cur;
                isOdd = false;
            } else {
                evenTail = cur;
                isOdd = true;
            }
            cur = curNext;
        }
        return head;
    }
}
```

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
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        
        ListNode prevOdd = head;
        ListNode prevEven = head.next;
        ListNode headEven = head.next;
        while (prevOdd != null && prevEven != null && prevEven.next != null) {
            ListNode curOdd = prevEven.next;
            ListNode curEven = curOdd.next;
                
            prevOdd.next = curOdd;
            prevEven.next = curEven;
            curOdd.next = headEven;
                
            prevOdd = curOdd;
            prevEven = curEven;
        }
        return head;
    }
}
```

