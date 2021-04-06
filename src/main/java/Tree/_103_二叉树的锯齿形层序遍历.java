package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class _103_二叉树的锯齿形层序遍历 {
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        //每一层的节点数量,默认为1(第一层只有根节点)
        int levelSize = 1;
        //每一层的高度,默认为1(第一层的高度为1)
        int height = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        //根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //头结点出队
            TreeNode node = queue.poll();
            if ((height & 1) == 1) {
                list.add(node.val);
            } else {
                list.add(0, node.val);
            }
            levelSize--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            //每一层访问完之后下一层的节点数量等于队列的size
            if (levelSize == 0) {
                levelSize = queue.size();
                lists.add(list);
                height++;
                list = new ArrayList<>();
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<List<Integer>> lists = zigzagLevelOrder(root);
        System.out.println(lists);
/*        List<Integer> lists = new ArrayList<>();
        lists.add(0, 1);
        lists.add(0, 2);
        System.out.println(lists);*/
    }
}
