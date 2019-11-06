## Problem
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
```
输入:

   1
 /   \
2     3
 \
  5
```
输出: ["1->2->5", "1->3"]

解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3

## Idea
1. 递归
2. Stack

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
 // 注意叶子节点
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        return traversal(root, new StringBuilder(""));
    }
    
    private List<String> traversal(TreeNode node, StringBuilder sb) {
        if (node.left == null && node.right == null) {
            sb.append(Integer.toString(node.val));
            List<String> res = new LinkedList<>();
            res.add(sb.toString());
            return res;
        }
        sb.append(node.val + "->");
        StringBuilder old = new StringBuilder(sb);
        if (node.left == null) {
            return traversal(node.right, sb);    
        }
        if (node.right == null) {
            return traversal(node.left, sb);
        }
        List<String> left = traversal(node.left, sb);
        List<String> right = traversal(node.right, old);
        for (String str : right) {
            left.add(str);
        }
        return left;
    }
}
```