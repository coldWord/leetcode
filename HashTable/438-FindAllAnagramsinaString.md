## Problem

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

```
输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
```

## Idea

两个哈希表，一个记录p的字符出现频率表，一个记录s中以p的长度为子串的字符频率出现表。然后滑动s的子串窗口[i,j]，每次对s中i-1位置的字符频率减1,j的位置加1

## Solution

```java
// time complexity: O(26*n) , n为s的长度
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // p的哈希表
        int[] counterP = new int[26];
        for (int i = 0; i < p.length(); ++i)
            counterP[p.charAt(i)-'a']++;
        // s的哈希表
        int[] counterS = new int[26];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length()-p.length()+1; ++i) {
            if (i == 0) {
                for (int j = 0; j < p.length(); ++j)
                    counterS[s.charAt(j)-'a']++;
            } else {
                counterS[s.charAt(i-1)-'a']--; // 窗口的头对应字符出现的次数减1
                counterS[s.charAt(p.length()+i-1)-'a']++; // 窗口的尾对应字符出现的次数加1
            }
            // check the anagrams
            boolean isAnagrams = true;
            for (int j = 0; j < 26; ++j) {
                if (counterS[j] != counterP[j]) {
                    isAnagrams = false;
                    break;   
                }
            }
            if (isAnagrams) res.add(i);
            isAnagrams = true;
        }
        return res;
    }
}
```

