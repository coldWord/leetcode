## 题目

给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

```
示例:

输入: head = 1->4->3->2->5->2, x = 3
输出: 1->2->2->4->3->5
```

## 思路

把大于或等于x的节点单独用一个链表L1连接起来，并从原链表移除这些节点和原链表的链接关系，最后把L1接到原来链表的后面。

## Solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode dummyFinal = new ListNode(-1);
        dummyFinal.next = head;
        ListNode prev = dummyFinal;
        ListNode dummyBig = new ListNode(-1);
        ListNode curBig = dummyBig;
        // 把大于等于x的节点移出来
        while (head != null) {
            if (head.val >= x) {
                ListNode cur = head;
                prev.next = head.next;
                head = head.next;
                cur.next = null;
                curBig.next = cur;
                curBig = curBig.next;
            } else {
                prev = prev.next;
                head = head.next;
            }
        }
        prev.next = dummyBig.next;
        return dummyFinal.next;
    }
}
```

