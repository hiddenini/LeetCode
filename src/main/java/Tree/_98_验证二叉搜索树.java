package Tree;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */
public class _98_验证二叉搜索树 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return false;
        /**
         * 这里不能用  Integer.MIN_VALUE
         */
        long pre = Long.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= pre) return false;
                pre = root.val;
                root = root.right;
            }
        }
        return true;
    }

    long pre = Long.MIN_VALUE;

    public boolean isValidBST1(TreeNode root) {
        return inOrder(root);
    }

    public boolean inOrder(TreeNode root) {
        if (root == null) return true;
        boolean l = inOrder(root.left);
        /**
         * 如果左子树不成立 直接返回false
         */
        if (!l) return false;
        if (root.val < pre) return false;
        boolean r = inOrder(root.right);
        return l && r;
    }


}
