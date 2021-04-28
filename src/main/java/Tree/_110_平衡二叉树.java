package Tree;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class _110_平衡二叉树 {

    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return false;
        }else{
            return Math.abs(height(root.left)-height(root.right))<=1 && isBalanced(root.left)&&isBalanced(root.right);
        }
    }

    int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public boolean isBalanced1(TreeNode root) {
        return balanced(root) != -1;
    }

    private int balanced(TreeNode node) {
        if (node == null) return 0;
        int leftHeight, rightHeight;
        if ((leftHeight = balanced(node.left)) == -1
                || (rightHeight = balanced(node.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
