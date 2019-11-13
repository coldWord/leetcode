### 注意点

- 考虑两个链表不等长的时候
  - 迭代指针为空指针时的处理
- 注意最后一位相加进位时的处理

## Solution(第二次)
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0; // 进位
        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        while (l1 != null || l2 != null) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int sum = val1 + val2 + carry;
            
            carry = sum / 10;
            sum = sum % 10;
            
            // l1长度小于或等于l2长度,把l2剩余部分接到l1后面
            if (l1.next == null && l2 != null) {
                l1.next = (l2.next != null) ? l2.next : null;
                l2.next = null;
            }
            
            l1.val = sum;
            
            if (l1.next == null && carry == 1) {
                l1.next = new ListNode(1);
                break;
            }
            l1 = l1.next;
            if (l2 != null) {
                l2 = l2.next;   
            }
        }
        return dummy.next;
    }
}
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

