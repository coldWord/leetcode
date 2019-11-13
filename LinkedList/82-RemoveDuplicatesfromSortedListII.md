## 题目

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 *没有重复出现* 的数字。

**示例 1:**

```
输入: 1->2->3->3->4->4->5
输出: 1->2->5
```

## 好的思路

因为排序列表，所以没必要用map来做，用一个迭代引用p和一个p.next的引用next，以next为起始开始遍历，如果next.val和p.val相等，删除next，迭代直到next.val与p.next不相等，同时用一个boolean型判断p的值是否出现重复。

## Solution
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * time complexity:O(n)
 * space complexity:O(1)
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        boolean isDuplicated = false;
        while (head != null) {
            // 删除重复节点(除了重复节点中最前面的那个节点)
            while (head.next != null && head.val == head.next.val) {
                isDuplicated = true;
                head.next = head.next.next;
            }
            // 判断当前节点是否是重复节点
            if (isDuplicated) {
                pre.next = head.next;
                head = head.next;
                isDuplicated = false;
            } else {
                pre = pre.next;
                head = head.next;   
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
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            ListNode next = head.next;
            boolean isDuplicated = false;
            while (next != null) {
                if (next.val == head.val) {
                    isDuplicated  = true;
                    head.next = next.next;
                    next = next.next;
                } else break;
            }
            if (isDuplicated) {
                prev.next = head.next;
            } else {
                prev = prev.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
```



## 最初的思路(差)

用一个HashMap以链表中节点的val为key，该val在链表出现的次数为value来存储，然后遍历，当map中value不为1时，删除遍历的节点。

## Solution 

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 时间复杂度: O(n)?
 * 空间复杂度: O(n)
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        Map<Integer, Integer> valCount = new HashMap<Integer, Integer>();
        while (head != null) {
            Integer val = valCount.get(head.val);
            if (val != null) 
                valCount.put(head.val, ++val);
            else
                valCount.put(head.val, 1);
            head = head.next;
        }
        head = dummy.next;
        ListNode prev = dummy;
        while (head != null) {
            if (!valCount.get(head.val).equals(1)) {
                prev.next = head.next;
                head = head.next;
            } else {
                prev = prev.next;
                head = head.next;
            }
        }
        return dummy.next;
    }
}
```

