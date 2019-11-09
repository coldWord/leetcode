## Problem
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```
返回如下的二叉树：
```
    3
   / \
  9  20
    /  \
   15   7
```

## Idea
分治，原问题为构建整个二叉树，子问题构建左子树的二叉树和右子树的二叉树，然后合并

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
    Map<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }
    
    // 返回root节点
    // 大问题构建整个二叉树，小问题构建左子树和右子树的二叉树
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        // 找到中序遍历中root的位置
        int rootPos = map.get(preorder[preStart]);
        int leftLen = rootPos-inStart;
        root.left = buildTree(preorder, preStart+1, preStart+leftLen, inorder, inStart, rootPos-1);
        root.right = buildTree(preorder, preStart+leftLen+1, preEnd, inorder, rootPos+1, inEnd);
        return root;
    }
}
```