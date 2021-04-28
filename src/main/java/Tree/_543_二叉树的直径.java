package Tree;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class _543_二叉树的直径 {
    int maxDepth = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDepth;
    }

    int depth(TreeNode node) {
        if (node == null) return 0;
        int L = depth(node.left);
        int R = depth(node.right);
        maxDepth = Math.max(L + R, maxDepth);
        return Math.max(L, R) + 1;
    }
}
