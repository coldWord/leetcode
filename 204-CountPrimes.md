## Problem

统计所有小于非负整数 *n* 的质数的数量。

**示例:**

```
输入: 10
输出: 4
解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
```

## Idea

 https://leetcode-cn.com/problems/count-primes/solution/ru-he-gao-xiao-pan-ding-shai-xuan-su-shu-by-labula/ 

## Solution

```java
class Solution {
    public int countPrimes(int n) {
        if (n <= 1) return 0;
        boolean[] notPrimes = new boolean[n];
        notPrimes[0] = true;
        notPrimes[1] = true;
        for (int i = 2; i < Math.sqrt(n); ++i) {
            if (!notPrimes[i]) {
                for (int j = 2; j * i < n; ++j) {
                    notPrimes[j*i] = true;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < notPrimes.length; ++i) {
            if (!notPrimes[i]) ++count;
        }
        return count;
    }
}
```

