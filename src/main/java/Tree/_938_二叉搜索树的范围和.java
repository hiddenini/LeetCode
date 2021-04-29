package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/range-sum-of-bst/
 */
public class _938_二叉搜索树的范围和 {
    /**
     * queue 层序遍历
     */
    public int rangeSumBST1(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (node.val > high) {
                    queue.offer(node.left);
                } else if (node.val < low) {
                    queue.offer(node.right);
                } else {
                    sum += node.val;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

        }
        return sum;
    }


    /**
     * dfs
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;

        if (root.val > high) return rangeSumBST(root.left, low, high);

        if (root.val < low) return rangeSumBST(root.right, low, high);

        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }

}
