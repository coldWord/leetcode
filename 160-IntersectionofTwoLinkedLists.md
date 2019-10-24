## 题目

编写一个程序，找到两个单链表相交的起始节点。

注意：

- 如果两个链表没有交点，返回 null.
- 在返回结果后，两个链表仍须保持原有的结构。
- 可假定整个链表结构中没有循环。
- 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。

## 思路1

计算两个链表的长度差，然后从相同长度的起始点开始共同迭代。

## Solution 1

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * 时间复杂度: O(n)
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // 把A和B放置到同一个起点上
        // O(n)
        while (lenA > lenB) {
            headA = headA.next;
            --lenA;
        }
        // O(n)
        while (lenA < lenB) {
            headB = headB.next;
            --lenB;
        }
        // O(n)
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }
    // O(n)
    private int length(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            ++length;
        }
        return length;
    }
}
```

## Solution 2

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
```

