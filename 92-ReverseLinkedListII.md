## 题目

反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

**说明:**
1 ≤ m ≤ n ≤ 链表长度。

```
示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
```

## 思路

把m到n的链表做前插操作，形成新链表，再连到原链表。

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) return head;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        dummy.next = head;
        int pos = 1;
        ListNode dummyReverseList = new ListNode(-1);
        // find the node which pos is m
        while (pos < m) {
            ++pos;
            head = head.next;
            prev = prev.next;
        }
        ListNode last = head;
        // reverse list from m to n
        while (pos <= n) {
            ++pos;
            ListNode next = head.next;
            head.next = dummyReverseList.next;
            dummyReverseList.next = head;
            head = next;
        }
        // connect lists
        prev.next = dummyReverseList.next;
        last.next = head;
        return dummy.next;
    }
}
```

