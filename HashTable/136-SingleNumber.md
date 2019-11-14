## Problem

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

## Good Idea

概念

如果我们对 0 和二进制位做 XOR(异或) 运算，得到的仍然是这个二进制位
a XOR 0 = a XOR 0=a
如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
a XOR a = a XOR a=0
XOR 满足交换律和结合律
a XOR b XOR a = (a XOR a) XOR b = 0 XOR b =b
所以我们只需要将所有的数进行 XOR 操作，得到那个唯一的数字。

>交换律：a ^ b ^ c <=> a ^ c ^ b  
>任何数于0异或为任何数 0 ^ n => n  
>相同的数异或为0: n ^ n => 0

## Solution

```java
// time complexity : O(n)
// space complexity : O(1)
class Solution {
    public int singleNumber(int[] nums) {
        for (int i = 1; i < nums.length; ++i) nums[0] ^= nums[i];
        return nums[0];
    }
}
```

## Bad Idea

遍历一遍nums，如果元素已经在HashSet中，就删除，否则添加；则剩下的那个就是所要的。

## Solution

```
// time complexity : O(n)
// space complexity : O(n)
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (s.contains(nums[i]))
                s.remove(nums[i]);
            else
                s.add(nums[i]);
        }    
        Iterator it = s.iterator();
        int singleNum = 0;
        while (it.hasNext())
            singleNum = (Integer)it.next();
        return singleNum;
    }
}
```

