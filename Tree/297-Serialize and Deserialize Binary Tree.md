## Problem


## Idea
1. 当做完全二叉树来处理
2. 遇到空节点不处理，其他节点加入队列

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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "n";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        // 空节点存为n
        StringBuilder res = new StringBuilder();
        int levelSize, nullNodeSize;
        while (true) {
            levelSize = q.size();
            nullNodeSize = 0; // 记录当前层的空节点数目
            StringBuilder levelStr = new StringBuilder();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode node = q.poll();
                if (node == null) {
                    levelStr.append("n");
                    q.offer(null); // 空节点也要加左右子节点
                    q.offer(null);
                    nullNodeSize++;   
                }
                else {
                    levelStr.append(Integer.toString(node.val));
                    q.offer(node.left);
                    q.offer(node.right);
                }
                levelStr.append(",");
            }
            if (levelSize == nullNodeSize) 
                break;
            res.append(levelStr);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("n")) return null;
        // 解码String为Integer
        StringBuilder sb = new StringBuilder();
        int cur = 0;
        for (; cur < data.length(); ++cur) {
            if (data.charAt(cur) == ',')
                break;
            sb.append(data.charAt(cur));
        }
        TreeNode root = new TreeNode(Integer.parseInt(sb.toString()));
        List<TreeNode> oldLevel = new LinkedList<>(); // 存储前一层的节点
        oldLevel.add(root);
        int oldLevelSize;
        cur++;
        while (cur < data.length()) {
            oldLevelSize = oldLevel.size();
            // 把当前层的节点都提取并存储
            List<TreeNode> curLevel = new LinkedList<>();
            sb = new StringBuilder(); // 存储每个节点的str
            int curLevelSize = 0; // 当前层应该有的节点数目oldLevelSize*2
            // 处理当前层
            while (curLevelSize < (oldLevelSize*2)) {
                if (data.charAt(cur) != ',')
                    sb.append(data.charAt(cur));
                else {
                    String str = sb.toString();
                    if (str.equals("n"))
                        curLevel.add(null);
                    else
                        curLevel.add(new TreeNode(Integer.parseInt(str)));
                    curLevelSize++;
                    sb.setLength(0); // 清空
                }
                cur++;
            }
            // 遍历前一层节点，与当前层连接
            for (int i = 0; i < oldLevelSize; ++i) {
                TreeNode node = oldLevel.get(i);
                if (node != null) {
                    node.left = curLevel.get(2*i);
                    node.right = curLevel.get(2*i+1);
                }
            }
            oldLevel = curLevel;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```