package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class _107_二叉树的层序遍历_2 {


    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        //每一层的高度,默认为1(第一层只有根节点)
        Queue<TreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int currentListSize = queue.size();
            /**
             * 确定每一层的节点数量,遍历完这个数量之后加入lists
             */
            for (int i = 1; i <= currentListSize; i++) {
                //头结点出队
                TreeNode node = queue.poll();
                currentList.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            lists.add(currentList);
        }
        return lists;
    }

}
