## Problem

给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

 

示例 1：

```
输入：
  s = "barfoothefoobarman",
  words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
```

## Idea

## Solution

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null || words.length == 0 || s.equals("")) return new ArrayList();
        Map<String, Integer> map = new HashMap();
        int wordLength = words[0].length();
        int wordsLength = words.length*words[0].length();
        List<Integer> res = new ArrayList();
        for (int i = 0; i < s.length()-wordsLength+1; ++i) {
            for (int j = 0; j < words.length; ++j) {
                if (!map.containsKey(words[j]))
                    map.put(words[j], 1);
                else
                    map.put(words[j], map.get(words[j])+1);
            }
            int k = i;
            while (k < s.length()) {
                String str = s.substring(k, k+wordLength);
                // String str = subString(s, k, wordLength);
                if (map.containsKey(str)) {
                    int count = map.get(str);
                    if (count == 1)
                        map.remove(str);
                    else
                        map.put(str, count-1);
                } else {
                    break;
                }
                if (map.isEmpty()) {
                    res.add(i);
                    break;
                }
                k += wordLength;
            }
            map.clear();
        }
        return res;
    }
    
    private String subString(String s, int start, int length) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < length) {
            sb.append(s.charAt(start+i));
        }
        return sb.toString();
    }
}
```

