## Problem
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
```
      0
     / \
   -3   9
   /   /
 -10  5
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return sorted(nums, 0, nums.length-1);
    }
    
    private TreeNode sorted(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = l + (r-l)/2; // 避免溢出
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sorted(nums, l, mid-1);
        node.right = sorted(nums, mid+1, r);
        return node;
    }
}
```