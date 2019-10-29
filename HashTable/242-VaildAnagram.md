## Problem

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

```
输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false
```


说明:
你可以假设字符串只包含小写字母。

进阶:
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

## Idea

用一个大小为26的数组对两个String中各个字母进行计数。(包含unicode字符用哈希表)

## Solution

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] counter = new int[26];
        for (char ch : s.toCharArray()) {
            counter[ch-'a']++;
        }
        for (char ch : t.toCharArray()) {
            counter[ch-'a']--;
        }
        for (int i = 0; i < counter.length; ++i) {
            if (counter[i] != 0) return false;
        }
        return true;
    }
}
```



