/******************************************
*   Create by ZhouHao
*
******************************************/

#include<iostream>
#include<string>

/*
*3. Longest Substring Without Repeating Characters 
*
*Given a string, find the length of the longest substring without repeating characters.
*Examples:
*Given "abcabcbb", the answer is "abc", which the length is 3.
*Given "bbbbb", the answer is "b", with the length of 1.
*Given "pwwkew", the answer is "wke", with the length of 3. 
*Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*
*
*/


class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        /*思路:
        *   maxLength记录不重复的子字符串的最大长度;
        *   从第一个字符开始出发，字符之后连续出现不重复的字符就加到result中，maxLength加一;
        *   对每个字符进行同样操作。
        */
        int length = s.size();
        int maxLength = 0;
        for(int i = 0; i < length; i++){
            string result = "";
            int tempLength = 0;
            for(int j = i; j < length; j++){
                if(result.find(s[j] , 0) == -1){
                    result += s[j];
                    tempLength += 1;
                }else{
                    break;
                }
            }
            if(maxLength < tempLength)
                maxLength = tempLength;
        }
        return maxLength;
    }
};

int main()
{
    return 0;
}