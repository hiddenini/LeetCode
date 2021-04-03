package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class _104_二叉树的最大深度 {
    /**
     * 使用层序遍历
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int height = 0;
        //每一层的高度,默认为1(第一层只有根节点)
        int levelSize = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //头结点出队
            TreeNode node = queue.poll();
            levelSize--;
            //System.out.println(node.element);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            //每一层访问完之后下一层的节点数量等于队列的size
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 递归
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));
    }
}
