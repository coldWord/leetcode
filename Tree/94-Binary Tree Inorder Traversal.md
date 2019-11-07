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
2. 迭代，使用一个额外变量来表示各个节点状态，第二次进stack就表示已访问，然后从栈弹出

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
        TreeNodeState (TreeNode node, boolean state) {
            this.node = node;
            isVisited = state;
        }
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        Stack<TreeNodeState> stack = new Stack<>();
        stack.push(new TreeNodeState(root, false));
        while (!stack.isEmpty()) {
            TreeNodeState node = stack.pop();
            if (node.node != null) {
                if (!node.isVisited) {
                    stack.push(new TreeNodeState(node.node.right, false));
                    stack.push(new TreeNodeState(node.node, true));
                    stack.push(new TreeNodeState(node.node.left, false));
                } else {
                    list.add(node.node.val);
                }   
            }
        }
        return list;
    }
}
```