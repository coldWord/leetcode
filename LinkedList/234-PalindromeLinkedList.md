## 题目

请判断一个链表是否为回文链表。

## 思路

反转后半部分链表，然后比较前半截链表和后半截链表。

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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        int len = length(head); // O(n)
        int mid = (len + 1)/2;
        ListNode pHead = head; 
        // 找到mid+1节点 O(n/2)
        while (mid > 0) {
            pHead = pHead.next;
            --mid;
        }
        pHead = reverseList(pHead); // O(n/2)
        // 判断mid+1与起始节点开始的之后的值 O(n/2)
        while (pHead != null) {
            if (pHead.val != head.val) return false;
            head = head.next;
            pHead = pHead.next;
        }
        return true;
    }
    
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            head = head.next;
            curr.next = prev;
            prev = curr;
            curr = head;
        }
        return prev;
    }
    
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

