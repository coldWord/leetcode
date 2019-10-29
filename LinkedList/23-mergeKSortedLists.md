## 题目

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

```
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

## 思路 1

找到K个链表的最小的那个节点，加到新链表中，并排除这个节点，后面做相同操作，直到遍历了所有节点

## Solution 1

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
 * 时间复杂度 O(2KN),N为总节点数，K为链表数
 */
class Solution {
public:
    bool hasElement(vector<ListNode*>& list) {
        vector<ListNode*>::iterator it;
        for (it = list.begin(); it != list.end(); it++) {
            if (*it)
                return true;
        }
        return false;
    }
    
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.empty())
            return nullptr;
        if (lists.size() < 2)
            return lists.at(0);
        vector<ListNode*>::iterator it;
        vector<ListNode*> pList; // 每个链表的迭代指针
        for (it = lists.begin(); it != lists.end(); it++) {
            pList.push_back(*it);
        }
        
        ListNode head = ListNode(-1);
        ListNode* pHead = &head;
        int min;
        while (hasElement(pList)) {
            // 第一个非空链表的第一个值作为min初始值
            int minPos;
            for (int i = 0; i < pList.size(); i++) {
                if (pList[i]) {
                    min = pList[i]->val;
                    minPos = i;
                    break;
                }
            }
            // 判断当前头节点最小值是哪个节点
            for (int i = minPos+1; i < pList.size(); i++) {
                if (pList[i]) {
                    if (pList[i]->val < min) {
                        min = pList[i]->val;
                        minPos = i;
                    }
                }
            }
            pHead->next = new ListNode(min);
            pHead = pHead->next;
            pList[minPos] = pList[minPos]->next; 
        }
        return head.next;
    }
};
```

## 思路 2 (分治方法)

两两配对合并，重复操作直到只有一个有序链表

## Solution 2

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
 * Suppose initially each list is of average length n, then:
	k/2*(2n) + k/4*(4n) + k/8*(8n)... + = logk * (kn)
 * 时间复杂度: O(KNlogK)
 * 空间复杂度: O(1)
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        ListNode dummy(-1); 
        ListNode *tail = &dummy;
        while (l1 && l2) {
            if (l1->val <= l2->val) {
                tail->next = l1;
                l1 = l1->next;
            } else {
                tail->next = l2;
                l2 = l2->next;
            }
            tail = tail->next;
        }
        tail->next = l1 ? l1 : l2;
        return dummy.next;
    }
    
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        if (lists.empty()) return NULL;
        int len = lists.size();
        while (len > 1) {
            for (int i = 0; i < len/2; ++i) {
                lists[i] = mergeTwoLists(lists[i], lists[len-1-i]);
            }
            len = (len+1)/2; // 奇偶不一样的值
        }
        return lists.front();
    }
};
```

