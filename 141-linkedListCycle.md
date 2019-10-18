## 题目

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

## 思路

用一个每次走一步的慢指针，一个每次走两步的块指针迭代，那么如果有环的话一定会在某一刻相遇

## Solution

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
    bool hasCycle(ListNode *head) {
        if (!head)
            return false;
        ListNode *slow = head, *fast = head;
        while(fast->next && fast->next->next) {
            slow = slow->next;
            fast = fast->next->next;
            if (slow == fast)
                return true;
        }
        return false;
    }
};
```

