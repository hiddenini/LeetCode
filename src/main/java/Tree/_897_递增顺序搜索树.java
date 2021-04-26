package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/increasing-order-search-tree/
 */
public class _897_递增顺序搜索树 {
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        //dfs(root, list);
        inorder(root, list);
        TreeNode dummy = new TreeNode(-1);
        TreeNode head = dummy;
        for (Integer value : list) {
            head.right = new TreeNode(value);
            head = head.right;
        }
        return dummy.right;
    }

    /**
     * 深度优先 递归
     */
    void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    /**
     * 迭代 使用栈
     */
    void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        Stack<TreeNode> stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }

    }

}
