package dfs;

import commom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum-ii/
 */
public class _113_路径总和_II {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        dfs(root, targetSum, new ArrayList<>(), list);
        return list;
    }

    private void dfs(TreeNode node, int remain, List<Integer> nums, List<List<Integer>> list) {
        if (node == null) return;
        remain -= node.val;
        nums.add(node.val);
        /**
         * 到达叶子节点才不继续往下钻了
         */
        if (node.left == null && node.right == null) {
            if (remain == 0) list.add(new ArrayList<>(nums));
        } else {
            dfs(node.left, remain, nums, list);
            dfs(node.right, remain, nums, list);
        }
        nums.remove(nums.size() - 1);
    }
}
