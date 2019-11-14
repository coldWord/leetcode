## C++必备知识

### map和unordered_map

map和unordered_map都是STL中的关联容器，每个元素都是pair，即关键字-值对，pair的第一元素是关键字，第二元素是值，map是按顺序保存的，unordered_map无序的，不允许键值重复。

## Solution
```java
// 边遍历边把访问过的值存到hash，如果存在，那么肯定会在前面出现
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            int com = target - nums[i];
            if (map.containsKey(com)) {
                res[0] = map.get(com);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
```

## Solution

```C++
/* 
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 */
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        int i, j;
        vector<int> res;
        for (i = 0; i < nums.size()-1; i++)
            for (j = i + 1; j < nums.size(); j++) {
                int sum = nums.at(i) + nums.at(j);
                if(target == sum) {
                    res.push_back(i);
                    res.push_back(j);
                }
            }
        return res;
    }
};

/* 
 * 时间复杂度 O(n^2)
 * 空间复杂度 O(1)
 * 边遍历边插，从nums[0]开始遍历插入，肯定得在两个元素均插入后map才能找到。（遍历到唯一正确的键对组的后一个元素时，前面元素肯定在hash中了）
 */
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> hash;
        for (int i = 0; i < nums.size(); i++) {
            int another = target - nums.at(i);
            if (hash.find(another) != hash.end()) {
                // found
                return vector<int> {hash[another], i};
            }
            hash[nums.at(i)] = i;
        }
        return vector<int> {};
    }
};
```

