## Problem

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

**示例:**

```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```


**说明：**

所有输入均为小写字母。
不考虑答案输出的顺序。

## Idea

用数组计数每个字符串中每个字母出现次数，在把数组转化成字符串作为Map的key,然后用HashMap来判断。

## Solution

```java
// time complexity：O(NK)，其中 N 是 strs 的长度，而 K 是 strs 中字符串的最大长度。计算每个字符串的字符串大小是线性的，我们统计每个字符串。
// space complexity：O(NK)，排序存储在 map 中的全部信息内容。
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> map = new HashMap<String, List>();
        int[] counter = new int[26]; // 计数每个字符串中每个字母出现次数,hash
        for (int i = 0; i < strs.length; ++i) {
            Arrays.fill(counter, 0);
            for (int j = 0; j < strs[i].length(); ++j) {
                ++counter[strs[i].charAt(j)-'a'];
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; ++j) {
                sb.append(counter[j]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(strs[i]);
        }
        return new ArrayList(map.values());
    }
}
```

