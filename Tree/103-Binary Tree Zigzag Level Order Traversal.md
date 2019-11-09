## Problem
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],
```
    3
   / \
  9  20
    /  \
   15   7
```
返回锯齿形层次遍历如下：
```
[
  [3],
  [20,9],
  [15,7]
]
```

## Idea
与层次遍历类似，设置一个方向变量，遍历完一层改变插入方向

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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean direction = true; // left
        while (!q.isEmpty()) {
            int levelSize = q.size();
            LinkedList<Integer> levelList = new LinkedList<>();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode node = q.poll();
                if (direction) {
                    levelList.add(node.val);
                } else {
                    levelList.addFirst(node.val);
                }
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            direction = !direction;
            res.add(levelList);
        }
        return res;
    }
}
```