## Problem
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，
```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
```
返回:
```
[
   [5,4,11,2],
   [5,8,4,5]
]
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumFrom(root, sum, path, paths);
        return paths;
    }
    
    private void pathSumFrom(TreeNode node, int sum, List<Integer> path, List<List<Integer>> paths) {
        if (node == null) return;
        path.add(new Integer(node.val));
        // leaf
        if (node.left == null && node.right == null && (sum - node.val) == 0) {
            paths.add(new ArrayList(path));
        } else {
            pathSumFrom(node.left, sum-node.val, path, paths);
            pathSumFrom(node.right, sum-node.val, path, paths);
        }
        path.remove(path.size()-1); // remove node
    }
}
```