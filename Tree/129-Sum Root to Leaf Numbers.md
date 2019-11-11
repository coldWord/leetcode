## Problem
给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。

例如，从根到叶子节点路径 1->2->3 代表数字 123。

计算从根到叶子节点生成的所有数字之和。

说明: 叶子节点是指没有子节点的节点。

示例 1:
```
输入: [1,2,3]
    1
   / \
  2   3
输出: 25
```
解释:
从根到叶子节点路径 1->2 代表数字 12.
从根到叶子节点路径 1->3 代表数字 13.
因此，数字总和 = 12 + 13 = 25.


## Idea
使用递归来解决，统计路径上大小时可以用int来存加快速度，不用字符串

## Solution
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int sumNumbers(TreeNode root) {
        return doSumNumbers(root, 0, 0);
    }
    
    private int doSumNumbers(TreeNode node, int curr, int sum) {
        if (node == null) return 0;
        curr *= 10;
        curr += node.val;
        if (node.left == null && node.right == null) {
            sum += curr;
        } else {
            sum = doSumNumbers(node.left, curr, sum) + doSumNumbers(node.right, curr, sum);
        }
        return sum;
    }
}
```