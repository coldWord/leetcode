## Problem

给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。

## Solution

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // value, index
        for (int i = 0; i < nums.length; ++i) {
            Integer frontIndex = map.get(nums[i]);
            if (frontIndex != null) {
                if ((i-frontIndex) <= k)
                    return true;
                else
                    map.put(nums[i], i);
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
```

