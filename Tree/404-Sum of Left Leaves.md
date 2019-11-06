## Problem
计算给定二叉树的所有左叶子之和。

示例：
```
    3
   / \
  9  20
    /  \
   15   7

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
```

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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;
        return doSumOfLeftLeaves(root.left, root)
            + doSumOfLeftLeaves(root.right, root);
    }
    
    private int doSumOfLeftLeaves(TreeNode node, TreeNode parent) {
        if (node == null) // 只有一边子树
            return 0;
        if (node.left == null && node.right == null) { // 叶子节点
            if (parent.left == node)
                return node.val;
            return 0;
        }
        return doSumOfLeftLeaves(node.left, node)
            + doSumOfLeftLeaves(node.right, node);
    }
}
```