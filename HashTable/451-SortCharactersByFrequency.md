## Problem

给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

示例 1:

```
输入:
"tree"

输出:
"eert"

解释:
'e'出现两次，'r'和't'都只出现一次。
因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
```

## Idea

思路1：先用哈希表存储频率表，然后用建立最大堆，在输出。

思路2：先用哈希表存储频率表，然后建立以频率为index，对应的字符组为值的数组，从尾部遍历到0。

## Solution

```java
// time complexity:接近O(n)
// 因为是给定的是字符串，可以用256大小的数组代替HashMap
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
                max = Math.max(max, map.get(ch));
            } else {
                map.put(ch, map.get(ch) + 1);
                max = Math.max(max, map.get(ch));
            }
        }
        // build a array that index represent the frequency of char
        String[] frequencyCh = new String[max+1];
        for (Character ch : map.keySet()) {
            int count = map.get(ch);
            String str = frequencyCh[count];
            frequencyCh[count] = (str == null) ? ""+ch : str+ch;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = max; i > 0; --i) {
            if (frequencyCh[i] != null) {
                for (char ch : frequencyCh[i].toCharArray()) {
                    int count = i;
                    while (count > 0) {
                        sb.append(ch);
                        --count;
                    }
                }
            }
        }
        return sb.toString();
    }
}
```

