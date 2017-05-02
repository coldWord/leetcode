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

class Solution_1 {
public:
    string longestPalindrome(string s) {
        /*
        *遍历字符串S的每一个子串，去判断这个子串是不是回文，是回文的话看看长度是不是比最大的长度maxlength大。
        *遍历每一个子串的方法要O（n^2），判断每一个子串是不是回文的时间复杂度是O(n)，所以暴力方法的总时间复杂度是O（n^3）。
        */
        int maxLength = 0, length = s.size();
        string subString = "";
        for(int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){
                int m = i, n = j;
                for(; m < n; m++, n--){
                    if(s[m] != s[n]){
                        break;
                    }
                }
                if(m >= n){
                    int tempLength = j - i + 1;
                    if(tempLength > maxLength){
                        maxLength = tempLength;
                        subString = s.substr(i, maxLength);
                    }
                }
            }
        }
        //只有一个字符或maxlength为1时
        if(maxLength == 0 || maxLength == 1){
            return s.substr(0, 1);
        }
        return subString;
    }
};

int main()
{
    return 0;
}