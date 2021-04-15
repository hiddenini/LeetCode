package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

public class _116_填充每个节点的下一个右侧节点指针 {
    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            levelSize--;
            if (levelSize > 0) {
                node.next = queue.peek();
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
            }
        }
        return root;
    }

    /**
     * 使用上一层的next指针 不需要队列
     */
    public Node connect1(Node root) {
        if (root == null) return null;
        Node farLeft = root;
        while (farLeft.left != null) {
            //遍历以farLeft为头节点的链表
            Node head = farLeft;
            while (head != null) {
                //链接自己的左右孩子
                head.left.next = head.right;

                //链接自己的右孩子和head.next的左孩子
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            //进入下一层的最左边节点
            farLeft = farLeft.left;
        }
        return root;
    }
}
