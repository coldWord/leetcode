## 题目

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 `null`。

## 思路 1

通过快慢指针可以判断有无环，在另设一个指针p指向初始点，那么相遇点和这个指针p一步步迭代会相遇于第一个入环点。（Floyd 算法）

数学证明见https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/

## Solution 2

```C++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
class Solution {
public:
    ListNode *detectCycle(ListNode *head) {
        ListNode *slow = head, *fast = head;
        while (fast && fast->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast) {
                ListNode *pHead = head;
                while (pHead != slow) {
                    pHead = pHead->next;
                    slow  = slow->next;
                }
                return slow;
            }
        }
        return NULL;
    }
};
```

## 思路2

设置一个set集合保存已访问的节点，遍历一边链表，第一个重复出现的节点就是入环点。