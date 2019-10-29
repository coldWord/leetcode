## Problem

给定一个整数数组，判断是否存在重复元素。

如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

## Idea

使用HashSet存储元素，有重复就返回true

## Solution

```java
// time complexity:O(n)
// space complexity:O(n)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (s.contains(nums[i])) return true;
            s.add(nums[i]);
        }
        return false;
    }
}
```

