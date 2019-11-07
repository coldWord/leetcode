## Problem
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

- 节点的左子树只包含小于当前节点的数。
- 节点的右子树只包含大于当前节点的数。
- 所有左子树和右子树自身必须也是二叉搜索树。
示例 1:
```
输入:
    2
   / \
  1   3
```

## Idea
1. 中序遍历，每次比较前面一个值和当前值的大小
2. 用栈，注意上下界

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
    private int prev = 0;
    private boolean isFirst = false;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (isValidBST(root.left)) {
            if (!isFirst) {
                isFirst = true;
                prev = root.val;
                return isValidBST(root.right);
            } else if (prev < root.val) {
                prev = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }
}
```