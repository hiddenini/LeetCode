package dp;

import commom.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/house-robber-iii/
 */
public class _337_打家劫舍_III {
    /**
     * f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和
     */
    Map<TreeNode, Integer> f = new HashMap<>();
    /**
     * g(o) 表示不选择 o 节点的情况下，o节点的子树上被选择的节点的最大权值和
     */
    Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 当 o 被选中时，o的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l和 r 不被选中的最大权值和相加
     * 即 f(o) = g(l) + g(r)f(o)=g(l)+g(r)。
     * <p>
     * 当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中。对于 o 的某个具体的孩子 x，
     * 它对 o 的贡献是 x 被选中和不被选中情况下权值和的较大值。
     * g(o) = max { f(l) , g(l)}+max\{ f(r) , g(r)}
     */

    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }


    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) +
                Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }


    /**
     * 优化
     */
    public int rob1(TreeNode root) {
        int[] rootStatus = dfs1(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs1(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs1(node.left);
        int[] r = dfs1(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

}
