## 题目

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

**示例：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

## Solution

```C++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
/**
 * 设h1为l1长度，h2为l2长度
 * 时间复杂度: O(max{h1,h2})
 * 空间复杂度: O(max{h1,h2}+1)
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode *pl1 = l1, *pl2 = l2;
        // ListNode *head = new ListNode(0); 内存泄漏
        // ListNode *pl3 = head;
        ListNode head = ListNode(0);
        ListNode *pl3 = &head;
        while (pl1 && pl2) {
            if (pl1->val <= pl2->val) {
                pl3->next = new ListNode(pl1->val);
                pl1 = pl1->next;
            } else {
                pl3->next = new ListNode(pl2->val);
                pl2 = pl2->next;
            }
            pl3 = pl3->next;
        }
        if (!pl1) {
            while (pl2) {
                pl3->next = new ListNode(pl2->val);
                pl2 = pl2->next;
                pl3 = pl3->next;
            }
        } else {
            while (pl1) {
                pl3->next = new ListNode(pl1->val);
                pl1 = pl1->next;
                pl3 = pl3->next;
            }
        }
        return head.next;
    }
};
```

