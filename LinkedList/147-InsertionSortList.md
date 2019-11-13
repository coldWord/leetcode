## Problem

对链表进行插入排序。

## Idea
已排好序的部分就不动了

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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head.next, tail = head;
        while (cur != null) {
            if (cur.val >= tail.val) {
                tail = cur;
                cur = cur.next;
            } else {
                tail.next = cur.next;
                ListNode p = dummy;
                while (p.next.val < cur.val) {
                    p = p.next;
                }
                cur.next = p.next;
                p.next = cur;
                cur = tail.next;
            }
        }
        return dummy.next;
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
// 当已排序好时多进行了许多没必要的比较
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode next, prevSorted, curSorted;
        while (head != null) {
            next = head.next;
            prevSorted = dummy;
            curSorted = dummy.next;
            head.next = null; // 断开当前节点与后续节点
            while (curSorted != null) {
                if (head.val <= curSorted.val) {
                    head.next = curSorted;
                    prevSorted.next = head;
                    break;
                } else {
                    prevSorted = prevSorted.next;
                    curSorted = curSorted.next;
                }
            }
            // head比已排序的链表所有节点的值都大
            if (curSorted == null) prevSorted.next = head;
            head = next;
        }
        return dummy.next;
    }
}
```

## Good Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 相比于前面的解法少了很多没必要的比较
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1), pre;
        dummy.next = head;
        while (head != null && head.next != null) {
            if (head.val <= head.next.val) {
                head = head.next;
                continue;
            }
            pre = dummy;
            while (pre.next.val < head.next.val) pre = pre.next;
            ListNode curr = head.next;
            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
        }
        return dummy.next;
    }
}
```

