## Problem
给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。


示例:
```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

提示：

你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

 
进阶：

你能在线性时间复杂度内解决此题吗？

## Idea
1. 暴力 O(NK)
2. [单调队列](https://leetcode-cn.com/problems/sliding-window-maximum/solution/dan-diao-dui-lie-by-labuladong/)

## Solution
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        int[] res = new int[nums.length-k+1];
        int maxPos = 0;
        int left, right;
        left = right = 0;
        // build a sliding window
        while (right < (left + k)) {
            if (nums[right] > nums[maxPos])
                maxPos = right;
            right++;
        }
        res[0] = nums[maxPos];
        int j = 1;
        while (right < nums.length) {
            left++;
            res[j] = nums[left];
            // 判断窗口内最大值
            for (int i = left+1; i <= right; ++i) {
                if (nums[i] > res[j])
                    res[j] = nums[i];
            }
            right++;
            j++;
        }
        return res;
    }
}
```