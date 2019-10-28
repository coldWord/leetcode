## Problem

编写一个算法来判断一个数是不是“快乐数”。

一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

示例: 

```
输入: 19
输出: true
解释: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
```

## Idea

用一个HashSet存储出现的所有数，如果在变为1的过程中出现重复就返回false;

## Solution

```java
// space complexity: O(n)
class Solution {
    public boolean isHappy(int n) {
        int res = n;
        Set<Integer> s = new HashSet<Integer>();
        while (res != 1) {
            if (s.contains(res)) return false;
            s.add(res);
            int origin = 0;
            while (res != 0) {
                int remainder = res % 10;
                res = (res - remainder) / 10;
                origin += remainder * remainder;
            }
            res = origin;
        }
        return true;
    }
}
```

## Idea (amazing)

用快慢指针来做 (空间复杂度可以达到O(1)）