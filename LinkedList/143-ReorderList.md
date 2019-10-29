##  Problem

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

