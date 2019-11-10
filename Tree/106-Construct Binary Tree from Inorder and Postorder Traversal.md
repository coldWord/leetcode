## Problem
根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出
```
中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
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
与前序和中序那题类似，后序遍历的最后一个节点是root节点，然后在中序遍历中找到root位置，在找到左子树和右子树的长度，于是问题就变成构建左子树和右子树的二叉树了(子问题)，利用递归得出，合并两个子树得到原问题的解。

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
    private Map<Integer, Integer> map;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        // find root node's index in inorder
        int rootIdx = map.get(root.val);
        root.left = buildTree(inorder, inStart, rootIdx-1, postorder, postStart, postStart+rootIdx-inStart-1);
        root.right = buildTree(inorder, rootIdx+1, inEnd, postorder, postStart+rootIdx-inStart, postEnd-1);
        return root;
    }
}
```