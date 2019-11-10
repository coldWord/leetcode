## Problem
给定一个二叉树，原地将它展开为链表。

例如，给定二叉树
```
    1
   / \
  2   5
 / \   \
3   4   6
```
将其展开为：
```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
```

## Idea
后序遍历，设置前驱节点

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
    public void flatten(TreeNode root) {
        flatten(root, null);
    }
    
    // 后序遍历
    private TreeNode flatten(TreeNode node, TreeNode prev) {
        if (node == null) return prev;
        prev = flatten(node.right, prev);
        prev = flatten(node.left, prev);
        node.right = prev;
        node.left = null;
        prev = node;
        return prev;
    }
}
```