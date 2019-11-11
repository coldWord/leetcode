## Problem
给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

提示：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。

## Idea
(要求常量级额外空间，所以不能用队列来做)
还是层次遍历的思路，使用上一层的next指针来调整。

## Solution
```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    public Node connect(Node root) {
        if (root == null) return null;
        Node level = root; // 当前层次遍历指针
        Node nextLevel = root.left; // 下一层
        while (nextLevel != null) {
            while (level != null) {
                level.left.next = level.right;
                if (level.next != null)
                    level.right.next = level.next.left;
                level = level.next;
            }
            level = nextLevel;
            nextLevel = nextLevel.left;
        }
        return root;
    }
}
```