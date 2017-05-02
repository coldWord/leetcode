/******************************************
*   Create by ZhouHao
*
******************************************/

#include<iostream>
#include<vector>

/*
*Given an array of integers, return indices of the two numbers such that they add up to a specific target.
*You may assume that each input would have exactly one solution, and you may not use the same element twice.
*Example:
*Given nums = [2, 7, 11, 15], target = 9,
*Because nums[0] + nums[1] = 2 + 7 = 9,
*return [0, 1].
*/

using namespace std;

/*
*   O(n^2)
*/

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> result;
        for(int i = 0; i < nums.size(); i++){
            for(int j = i + 1; j < nums.size(); j++){
                if((nums[i] + nums[j]) == target){
                    result.push_back(i);
                    result.push_back(j);
                    return result;
                }
            }
        }
        return result;
    }
};

int main()
{
    int arr[] = {2, 7, 11, 12},target = 9;
    vector<int> nums(arr, arr+4), result;
    Solution s;
    result = s.twoSum(nums, target);
    for(int i = 0; i < result.size(); i++)
        cout<<result[i]<<endl;
    return 0;
}