## 题目

删除链表中等于给定值 **val** 的所有节点。

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.val == val)
                prev.next = head.next;
            else 
                prev = prev.next;
            head = head.next;
        }
        return dummy.next;
    }
}
```

