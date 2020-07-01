## problem
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。

示例 1：
```
输入： 2
输出： 2
解释： 有两种方法可以爬到楼顶。
1.  1 阶 + 1 阶
2.  2 阶
```
示例 2：
```
输入： 3
输出： 3
解释： 有三种方法可以爬到楼顶。
1.  1 阶 + 1 阶 + 1 阶
2.  1 阶 + 2 阶
3.  2 阶 + 1 阶
```

## Idea
具有最优子结构性质。

爬到第i个阶梯需要的方法由下面两个过程组成：
1. 第i-1个阶梯的方法数
2. 第i-2个阶梯的方法数

## Solution
```java
// O(n)
// O(n)
class Solution {
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n+1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; ++i)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }
}
```

## Solution(Optimal)
```java
// O(n)
// O(1)
class Solution {
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int first = 1, second = 2;
        for (int i = 3; i <= n; ++i) {
            int tmp = first + second;
            first = second;
            second = tmp;
        }
        return second;
    }
}
```
