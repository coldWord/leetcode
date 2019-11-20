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
变形的后序遍历，设置前驱节点

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
```java
class Solution {
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
            } else {
                // 1、找root左子树的最右叶子节点pre
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                // 2、将root的右子树接为最右节点pre的右孩子
                pre.right = root.right;
                // 3、将root的整个左子树接为root的右子树
                root.right = root.left;
                // 4、root的左子树为null
                root.left = null;
                // 5、递归root的右节点
                root = root.right;
            }
        }
    }
}
```