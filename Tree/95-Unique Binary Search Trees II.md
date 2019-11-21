## Problem
给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。

示例:
```
输入: 3
输出:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

## Idea
1. 递归
2. 动态规划

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
 // 分治，大问题[1...n]的搜索树
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new LinkedList<>();
        return doGenerateTrees(1, n);
    }
    
    public List<TreeNode> doGenerateTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if(start > end){
            res.add(null);
            return res;
        }

        // pick up a root
        for (int i = start; i <= end; ++i) {
            // all possible left subtrees if i is choosen to be a root
            List<TreeNode> subLeftTree = doGenerateTrees(start, i-1);
            // all possible right subtrees if i is choosen to be a root
            List<TreeNode> subRightTree = doGenerateTrees(i+1, end);
            // connect left and right trees to the root i
            for(TreeNode left : subLeftTree){
                for(TreeNode right : subRightTree){
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    res.add(node);
                }
            }
        }
        return res;
    }
}
```

