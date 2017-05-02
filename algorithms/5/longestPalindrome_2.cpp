/*********************************************
* Create by ZhouHao 
*
**********************************************/
/*
*Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
*Example:
*Input: "babad"
*Output: "bab"
*Note: "aba" is also a valid answer.
*Example:
*Input: "cbbd"
*Output: "bb"
*/

#include <iostream>
#include <string>

class Solution_2 {
public:
    string longestPalindrome(string s) {
        /*
        *  O(n^2)
        */
        int maxLength = 0, length = s.size(), start = 0;
        string subString = "";
        //ababa
        for(int i = 0; i < length; i++){
            int l = i - 1;
            int r = i + 1;
            while(l >= 0 && r < length && s[l] == s[r]){
                if((r - l + 1) > maxLength){
                    maxLength = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
        }
        //bbaabb
        for(int i = 0; i < length; i++){
            int l = i;
            int r = i + 1;
            while(l >= 0 && r < length && s[l] == s[r]){
                if((r - l + 1) > maxLength){
                    maxLength = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
        }
        if(maxLength > 0){
            return s.substr(start, maxLength);
        }
        if(maxLength == 0){
            return s.substr(0, 1);
        }
    }
};

int main()
{
    return 0;
}
