## Problem
给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

示例：

```
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
```


说明：

如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。

## Idea

Sliding Window + Two Pointer + Hash

先right指针找到一个包含t中所有字符的可行窗口，然后右移left指针，更新最小窗口，直到没有可行解，再右移right指针直到有可行窗口，重复前面过程，直到到达末尾。

> 1.判断是否有可行解，可以通过一个计数不同字符的变量来完成
>
> 2.注意minLength取值和返回值

## Solution
```java
// time complexity: O(n+m)
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        if (s.length() == 0 || t.length() == 0) return "";
        int[] needs = new int[128];
        int needsCount = 0; // 记录t不重复的字符数
        for (int i = 0; i < t.length(); ++i) {
            if (needs[(int)t.charAt(i)] == 0)
                needsCount++;
            needs[(int)t.charAt(i)]++;
        }
        
        int[] window = new int[128];
        int left, right;
        left = right = 0;
        // match 记录滑动窗口有多少字符符合要求
        int match = 0;
        // 注意minLength取值要为-1
        int minLength = -1, beginIndex = 0, endIndex = 0;
        while (right < s.length()) {
            window[(int)s.charAt(right)]++;
            // 该字符出现在t中
            if (needs[(int)s.charAt(right)] != 0) {
                if (needs[(int)s.charAt(right)] == window[(int)s.charAt(right)])
                    match++;
            }
            // 判断滑动窗口是否符合要求
            while (match == needsCount) {
                // 判断当前窗口长度是不是最小
                int tmp = right - left + 1;
                if (tmp <= minLength || minLength == -1) {
                    minLength = tmp;
                    beginIndex = left;
                    endIndex = right;
                }
                // left左移
                if (needs[(int)s.charAt(left)] != 0) {
                    window[(int)s.charAt(left)]--;
                    if (window[(int)s.charAt(left)] < needs[(int)s.charAt(left)])
                        match--;
                }
                left++;
            }
            right++;
        }
        return minLength == -1 ? "" : s.substring(beginIndex, endIndex+1);
    }
}
```