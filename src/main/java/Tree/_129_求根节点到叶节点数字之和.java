package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 129. 求根节点到叶节点数字之和
 */
public class _129_求根节点到叶节点数字之和 {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    /**
     * 深度优先遍历,递归
     */
    private int dfs(TreeNode root, int prevSum) {
        if (root == null) return 0;
        int sum = root.val + prevSum * 10;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    /**
     * 广度优先遍历
     * <p>
     * 使用广度优先搜索，需要维护两个队列，分别存储节点和节点对应的数字。
     * <p>
     * 初始时，将根节点和根节点的值分别加入两个队列。每次从两个队列分别取出一个节点和一个数字，进行如下操作：
     * <p>
     * 如果当前节点是叶子节点，则将该节点对应的数字加到数字之和；
     * <p>
     * 如果当前节点不是叶子节点，则获得当前节点的非空子节点，并根据当前节点对应的数字和子节点的值计算子节点对应的数字，然后将子节点和子节点对应的数字分别加入两个队列。
     * <p>
     * 搜索结束后，即可得到所有叶子节点对应的数字之和。
     *
     */
    public int sumNumbers1(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer num = numQueue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left == null && right == null) {
                sum += num;
            }
            if (left != null) {
                nodeQueue.offer(left);
                numQueue.offer(num * 10 + left.val);
            }
            if (right != null) {
                nodeQueue.offer(right);
                numQueue.offer(num * 10 + right.val);
            }
        }
        return sum;
    }

}
