## Problem
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:
```
给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。
```
示例 2:
```
给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
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
    public boolean isBalanced(TreeNode root) {
        return treeDepth(root, 0) != -1;
    }
    
    // -1代表不平衡
    private int treeDepth(TreeNode node, int depth) {
        if (node == null) return depth;
        int left = treeDepth(node.left, ++depth); // 获取左树高度
        if (left == -1)
            return -1;
        int right = treeDepth(node.right, depth); // 获取右树高度
        if (right == -1)
            return -1;
        if (Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right);
    }
}
```