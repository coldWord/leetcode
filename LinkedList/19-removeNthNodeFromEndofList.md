## 题目

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

- 示例：

```
给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。
```

- 进阶：

你能尝试使用一趟扫描实现吗？

### 思路

设置三个指针，pEnd指向链表的**最终NULL节点**，pDel指向**pEnd指针的前n个位置**(也就是我们要删除的节点)，pre指向pDel的前一个节点。(假设pEnd和pDel初值都是头节点，同时遍历，那么pEnd到达NULL节点时，我们就可以用pDel表示倒数的n个节点了)。可以到达一遍扫描实现该任务的目的。

### Solution

```C++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        // pEnd指向最后的NULL节点，pDel在pEnd的前n个位置(要删除的节点)，pre指向pDel前一个指针
        ListNode *pEnd = head, *pDel = head, *pre = nullptr;
        while (n > 0) {
            pEnd = pEnd->next;
            --n;
        }
        while (pEnd) {
            pre = pDel;
            pEnd = pEnd->next;
            pDel = pDel->next;
        }
        // 要删除的是第一个节点
        if (!pre)
            head = head->next;
        else
            pre->next = pDel->next;
        return head;
    }
};
```