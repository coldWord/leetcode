## Problem
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例: 
```
你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
```

## Idea
1. 当做完全二叉树来处理
2. 序列化时，队列遇到空节点序列化为null，其他节点把子节点加入队列；
   1. 反序列化时，除了根节点，后面的节点必须成对出现，且第一个是某节点的左子节点，第二个是其右子节点。
   2. 如果遇到的不是null，就要后移两个作为子节点。

3. 用先序遍历?

## Solution
```java
// 递归实现
private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
```

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
    // 下一层的节点数目始终是上一层不为空节点数目的两倍
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "n";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                q.offer(node.left);
                q.offer(node.right);
                res.append(Integer.toString(node.val));
            } else {
                res.append("n");
            }
            res.append(",");
        }
        return res.toString();
    }

    // 下一层的节点数目始终是上一层不为空节点数目的两倍
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("n")) return null;
        String[] strArray = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(strArray[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1; // child node index
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node != null) {
                if (strArray[i].equals("n"))
                    node.left = null;
                else
                    node.left = new TreeNode(Integer.parseInt(strArray[i]));
                if (strArray[i+1].equals("n"))
                    node.right = null;
                else
                    node.right = new TreeNode(Integer.parseInt(strArray[i+1]));
                q.offer(node.left);
                q.offer(node.right);
                i += 2;
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## Solution(bad)
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