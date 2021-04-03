package Tree;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
public class _124_二叉树中的最大路径和 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) return 0;
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));
        /**
         *节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
         */
        int currentGain = node.val + leftGain + rightGain;
        /**
         * 更新全局的最大值
         */
        maxSum = Math.max(maxSum, currentGain);
        /**
         * 返回该节点的贡献值
         */
        return node.val + Math.max(leftGain, rightGain);
    }
}
