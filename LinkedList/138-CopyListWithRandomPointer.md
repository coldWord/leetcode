## Problem

给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

要求返回这个链表的**深拷贝**。 

## Idea

1. 用一个map存储原来节点引用与pos的关系(便于复制该节点时能快速确定random指针指向的位置号)
2. 用一个map存储复拷贝节点引用和pos的关系,这一步拷贝节点的random位置还不能进行指向
3. 对每个拷贝节点的random指针进行指向，先得到原来链表对应节点random的pos，在通过pos得到拷贝节点random的ref

## Solution

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    public Node copyRandomList(Node head) {
        // 用一个map存储节点引用与pos的关系(便于复制该节点时能快速确定random指针指向的位置号)
        Map<Node, Integer> nodePos = new HashMap<Node, Integer>();
        Node pHead = head;
        int pos = 1;
        while (pHead != null) {
            nodePos.put(pHead, pos);
            pHead = pHead.next;
            ++pos;
        }
        // 用一个map存储复制节点引用和pos的关系
        Map<Integer, Node> copyNodePos = new HashMap<Integer, Node>();
        Node dummy = new Node(-1);
        Node pCopy = dummy;
        pHead = head;
        pos = 1;
        while (pHead != null) {
            Node cur = new Node(pHead.val, null, null);
            pCopy.next = cur;
            pCopy = pCopy.next;
            pHead = pHead.next;
            copyNodePos.put(pos, cur);
            ++pos;
        }
        // 对每个节点的random指针进行指向
        pCopy = dummy.next;
        pHead = head;
        while (pCopy != null) {
            // 先得到原来链表节点random的pos，在通过pos得到node的ref
            pCopy.random = copyNodePos.get(nodePos.get(pHead.random));
            pCopy = pCopy.next;
            pHead = pHead.next;
        }
        return dummy.next;
    }
}
```

