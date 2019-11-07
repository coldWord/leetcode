## Problem
给定一个二叉树，返回它的 后序 遍历。

示例:
```
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [3,2,1]
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

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
    class TreeNodeState {
        public TreeNode node;
        public boolean isVisited;
        TreeNodeState(TreeNode node, boolean state) {
            this.node = node;
            isVisited = state;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNodeState> stack = new Stack<>();
        stack.push(new TreeNodeState(root, false));
        while (!stack.isEmpty()) {
            TreeNodeState nodeState = stack.pop();
            TreeNode node = nodeState.node;
            if (node != null) {
                if (!nodeState.isVisited) {
                    stack.push(new TreeNodeState(node, true));
                    stack.push(new TreeNodeState(node.right, false));
                    stack.push(new TreeNodeState(node.left, false));
                } else {
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
```