### 注意点

- 考虑两个链表不等长的时候
  - 迭代指针为空指针时的处理
- 注意最后一位相加进位时的处理

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
  * 时间复杂度: O(max{h1,h2}) (h1是l1链表的长度，h2是l2链表的长度)
  */ 
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode *pl1 = l1, *pl2 = l2;
        ListNode *res = new ListNode(0), *head = res;
        int carry_set = 0, sum = 0;
        while (pl1 || pl2) {
            // l1位数更小
            if (!pl1) 
                sum = pl2->val + carry_set;
            // l2位数更小
            else if (!pl2)
                sum = pl1->val + carry_set;
            else
                sum = pl1->val + pl2->val + carry_set;
            if (sum >= 10) {
                res->val = sum - 10;
                carry_set = 1;
            } else {
                res->val = sum;
                carry_set = 0;
            }
            if (pl1) {
                pl1 = pl1->next;
            }
            if (pl2) {
                pl2 = pl2->next;   
            }
            if (pl1 || pl2)
                res->next = new ListNode(0);
            else {
                // 最后一位进位
                if (carry_set)
                    res->next = new ListNode(1);
            }
            res = res->next;
        }
        return head;
    }
};
```

