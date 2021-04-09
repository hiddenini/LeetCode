package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 */
public class _101_对称二叉树 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return false;
        return check(root.left, root.right);
    }

    /**
     * 递归
     */
    private boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }

    /**
     * 使用队列
     */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return false;
        return check1(root.left, root.right);
    }

    private boolean check1(TreeNode pre, TreeNode cur) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pre);
        queue.offer(cur);
        while (!queue.isEmpty()) {
            pre = queue.poll();
            cur = queue.poll();
            if (pre == null && cur == null) continue;
            if ((pre == null || cur == null) || pre.val != cur.val) return false;
            queue.offer(pre.left);
            queue.offer(cur.right);

            queue.offer(pre.right);
            queue.offer(cur.left);
        }
        return true;
    }
}
