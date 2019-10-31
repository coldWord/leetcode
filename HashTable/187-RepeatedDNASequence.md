## Problem

所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。

编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。

示例：

```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC", "CCCCCAAAAA"]
```

## Solution

```java
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return new ArrayList();
        List<String> list = new ArrayList<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i+9 < s.length(); ++i) {
            String substr = s.substring(i,i+10);
            Integer count = map.get(substr);
            if (count == null) {
                map.put(substr, 1);  
            } else if (count == 1){
                list.add(substr);
                map.put(substr, 2);
            }
        }
        return list;
    }
}
```

