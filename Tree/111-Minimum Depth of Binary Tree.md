## Problem
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:
```
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回它的最小深度  2.
```

## Idea
注意只有一边子树的情况

## Solution
```java
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return doMinDepth(root, 1, -1);
    }

    // 返回最小深度
    private int doMinDepth(TreeNode node, int depth, int minDepth) {
        if (node == null) return minDepth;
        // if node is leaf
        if (node.left == null && node.right == null) {
            if (depth < minDepth || minDepth == -1) return depth;
            return minDepth;
        }
        minDepth = doMinDepth(node.left, depth+1, minDepth);
        minDepth = doMinDepth(node.right, depth+1, minDepth);
        return minDepth;
    }
}
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 && right == 0)
            return 1;
        if (left == 0 || right == 0)
            return Math.max(left, right) + 1;
        return Math.min(left, right) + 1;
    }
}
```