package Tree;

/**
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 *
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/solution/4chong-zhong-xu-bian-li-de-fang-fa-morri-p6ot/
 */
public class _783_二叉搜索树节点最小距离 {
    int pre = -1;
    int ans = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return ans;
    }

    void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (pre < 0) {
            pre = node.val;
        } else {
            ans = Math.min(ans, node.val - pre);
            pre = node.val;
        }
        inorder(node.right);
    }
}
