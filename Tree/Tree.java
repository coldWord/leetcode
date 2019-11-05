import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Tree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { 
            val = x; 
            left = null;
            right = null;
        }
    }

    TreeNode root;

    Tree(TreeNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 中序遍历
    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.val + " ");
            inOrder(node.right);
        }
    }

    // 后序遍历
    public void postOrder(TreeNode node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.val + " ");
        }
    }

    public int levelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return 0; 
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
                System.out.print(node.val + " ");
            }
            level++;
            System.out.println();
        }
        return level;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;

        Tree t = new Tree(node1);
        t.preOrder(node1);
        System.out.println();
        t.inOrder(node1);
        System.out.println();
        t.postOrder(node1);
        System.out.println();

        System.out.println("level = " + t.levelOrder(node1));
    }
}