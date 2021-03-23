package Tree;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class _236_二叉树的最近公共祖先 {
    /**
     * 去以root为根节点的二叉树中查找p q的公共祖先
     * <p>
     * 如果p,q同时存在于二叉树中,就能成功返回它们的公共祖先
     * <p>
     * 如果都不存在于二叉树中,返回null
     * <p>
     * 如果只有p存在于这个二叉树中,返回p
     * <p>
     * 如果只有q存在于这个二叉树中,返回q
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        /**
         *去以root.left为根节点的二叉树中查找p q的公共祖先
         */
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        /**
         * 去以root.right为根节点的二叉树中查找p q的公共祖先
         */
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }
}
