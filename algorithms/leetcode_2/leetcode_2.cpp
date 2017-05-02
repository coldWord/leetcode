/******************************************
*   Create by ZhouHao
*
******************************************/


#include<iostream>

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };


class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* l3 = new ListNode(-1);
        ListNode* pl3 = l3;
        int carry = 0;//进位
        while(true){
            int sum = carry;
            if(l1 == NULL && l2 == NULL){
                break;
            }
            if(l1 != NULL){
                sum += l1->val;
                l1 = l1->next;
            }
            if(l2 != NULL){
                sum += l2->val;
                l2 = l2->next;
            }
            carry =sum / 10;
            ListNode* tmp = new ListNode(sum % 10);
            pl3->next = tmp;
            pl3 = pl3->next;
        }
        if(carry == 1){
            ListNode* tmp = new ListNode(1);
            pl3->next = tmp;
        }
        return l3->next;
    }
};

int main()
{
    return 0;
}