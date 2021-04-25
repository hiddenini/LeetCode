package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
public class _199_二叉树的右视图 {

    /**
     * 使用层序遍历 每次保留每一层的最后一个节点 BFS
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            size--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            if (size == 0) {
                size = queue.size();
                list.add(node.val);
            }
        }
        return list;
    }

    /**
     * dfs
     */
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView1(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) return;
        if (depth == res.size()) res.add(root.val);
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        _199_二叉树的右视图 obj = new _199_二叉树的右视图();
        for (Integer integer : obj.rightSideView1(root)) {
            System.out.println(integer);
        }
    }
}
