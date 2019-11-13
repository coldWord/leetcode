## 题目

在 *O*(*n* log *n*) 时间复杂度和常数级空间复杂度下，对链表进行排序。

## 思路 1

使用归并排序(递归时空间复杂度不为O(1))

## Solution 1

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
    public ListNode sortList(ListNode head) {
        int length = length(head);
        if (length > 1) {
            int count = length/2;
            ListNode mid = head;
            while (--count > 0)
                mid = mid.next;
            ListNode l2 = mid.next;
            mid.next = null; //l1的尾置为null
            ListNode l1 = sortList(head);
            l2 = sortList(l2);
            return merge(l1, l2);
        }
        return head;
    }
    
    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }
    // 合并两个有序链表，链表尾为null
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        dummy.next = l1;
        ListNode prev = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                l1 = l1.next;
            } else {
                ListNode tmp = l2;
                l2 = l2.next;
                prev.next = tmp;
                tmp.next = l1;
            }
            prev = prev.next;
        }
        prev.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }
}
```

## 思路2

使用归并排序的非递归方式

## Solution 2

```java
class Solution {
    public ListNode sortList(ListNode head) {
        int length = length(head);
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int step = 1; step <= length; step *= 2) {
            ListNode prev = dummy;
            ListNode cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = split(left, step);
                cur = split(right, step);
                prev = merge(left, right, prev); // prev引用很妙，指向已排序链表的尾部
            }
        }
        return dummy.next;
    }
    
    /*
     * 根据n的数目，把head链表分成两链表，一个前n个节点，末尾节点next置为null，另一个是链表的剩余的部分
     * 返回值是另一部分的头节点
     */
    private ListNode split(ListNode head, int n) {
        if (head == null) return null;
        for (int i = 1; head != null && i < n; ++i) head = head.next;
        if (head != null) {
            ListNode l2 = head.next;
            head.next = null;
            return l2;
        }
        return null;
    }
    
    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }
    
    /*
     * 返回合并后的末尾引用,引入一个prev引用,prev是已排序好的链表末尾，连接prev和l1、l2合并后的链表
     */
    private ListNode merge(ListNode left, ListNode right, ListNode prev) {
        ListNode cur = prev;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            }
            else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        
        if (left != null) cur.next = left;
        else if (right != null) cur.next = right;
        while (cur.next != null) cur = cur.next;
        return cur;
    }
}
```

