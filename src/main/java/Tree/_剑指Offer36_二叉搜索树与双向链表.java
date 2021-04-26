package Tree;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class _剑指Offer36_二叉搜索树与双向链表 {
    Node head, tail;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;
    }

    void dfs(Node cur) {
        if (cur == null) return;
        dfs(cur.left);

        if (tail != null) {
            tail.right = cur;
        } else {
            head = cur;
        }
        cur.left = tail;
        tail = cur;

        dfs(cur.right);
    }
}
