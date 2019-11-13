##  Problem
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:
```
给定链表 1->2->3->4, 重新排列为 1->4->2->3.
```
示例 2:
```
给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```
## Idea(Second)
用快慢指针将链表分段，slow指针的后一个节点是要插入到前面的起始点。
1. 可以用栈存储后半部分达到逆序效果
2. 直接逆序后半部分

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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow指针之后的节点是要插入的节点
        ListNode tail = slow;
        slow = slow.next;
        tail.next = null;
        slow = reverseList(slow);
        while (slow != null) {
            ListNode resNext = slow.next;
            ListNode headNext = head.next;
            slow.next = head.next;
            head.next = slow;
            slow = resNext;
            head = headNext;
        }
    }
    
    /**
     * 反转链表,返回链表头
     */
    private ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = next;
        }
        return dummy.next;
    }
}
```

## 思路

快慢指针来求中间节点的位置，然后要断开两部分链表，然后逆序后半部分链表，在插入到前半部分链表中。

**NOTE：**要注意链表长为奇偶时不同的后半部分链表起始点

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
    public void reorderList(ListNode head) {
        if (head == null) return;
        // 先通过快慢指针找到后半部分链表
        ListNode prev = new ListNode(-1);
        prev.next = head;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            prev = prev.next;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = slow;
        // 断开两个链表
        if (fast == null) // 偶数
            prev.next = null;
        else { // 奇数
            cur = slow.next;
            slow.next = null;
        }
        // 逆序后半部分链表
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        cur = head;
        ListNode pRes = dummy.next;
        while (pRes != null) {
            ListNode next = cur.next;
            cur.next = pRes;
            ListNode resNext = pRes.next;
            pRes.next = next;
            
            pRes = resNext;
            cur = next;
        }
    }
}
```

