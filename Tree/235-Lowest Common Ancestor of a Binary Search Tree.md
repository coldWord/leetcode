## Problem
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null | q == null) return null;
        // p, q值出现在第一个root两列时
        if (root.val < p.val && root.val > q.val) return root;
        if (root.val > p.val && root.val < q.val) return root;
        if (root.val == p.val || root.val == q.val) return root;
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        // if (root.val > p.val && root.val > q.val)
        return lowestCommonAncestor(root.left, p, q);
    }
}
```