## Problem

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

```
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

示例 2:

```
输入: "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

示例 3:

```
输入: "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

## Idea (Optimized)

使用sliding window.

## Solution
```java
// O(2n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int maxLength = 0, length = 0;
        int left = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                length++;
            } else {
                int dup = map.get(s.charAt(i)); // find the duplicated char
                for (; left <= dup; ++left) {
                    map.remove(s.charAt(left));
                    length--;
                }
                length++;
                map.put(s.charAt(i), i);
            }
            if (length > maxLength) maxLength = length;
        }
        return maxLength;
    }
}
```

## Solution

```java
// time complexity: O(2n)
// space complexity: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<Character>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                ++j;
                maxLength = Math.max(maxLength, set.size());
            } else {
                set.remove(s.charAt(i));
                ++i;
            }
        }
        return maxLength;
    }
}
```



## Idea

用哈希集存储子串，然后从头开始以每个字符为起始遍历字符串

## Solution

```java
// time complexity: O(n^2)
// space complexity: O(n)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0, length = 0;
        Set<Character> set = new HashSet<Character>();
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            length = 0;
            for (int j = i; j < charArray.length; ++j) {
                if (set.contains(charArray[j])) {
                    break;
                } else {
                    set.add(charArray[j]);
                    ++length;
                }
            }
            if (length > maxLength) maxLength = length;
            set.clear();
        }
        return maxLength;
    }
}
```

