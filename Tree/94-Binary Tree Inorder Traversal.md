## Problem
给定一个二叉树，返回它的中序 遍历。

示例:
```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

## Idea
1. 递归
2. 迭代

## Solution(recursive)
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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        doInorderTraversal(root, list);
        return list;
    }
    
    private void doInorderTraversal(TreeNode node, List<Integer> list) {
        if (node != null) {
            doInorderTraversal(node.left, list);
            list.add(node.val);
            doInorderTraversal(node.right, list);
        }
    }
}
```

## Solution(Iteratively)
```java

```