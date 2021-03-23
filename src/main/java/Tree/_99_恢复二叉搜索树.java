package Tree;

/**
 * https://leetcode-cn.com/problems/recover-binary-search-tree/
 */
public class _99_恢复二叉搜索树 {
    /**
     * 二叉搜索树的中序遍历是升序的
     *
     * @param root
     */
    TreeNode prev;
    //第一个错误节点
    TreeNode first;
    //第二个错误节点
    TreeNode second;

    public void recoverTree(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                //找到前驱节点 左子树一直往右走
                TreeNode pred = node.left;
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    pred.right = node;
                    node = node.left;
                } else {//  pred.right = node;
                    find(node);
                    pred.right = null;
                    node = node.right;
                }

            } else {
                find(node);
                node = node.right;
            }
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

    }

    private void find(TreeNode node) {
        //出现逆序对
        if (prev != null && prev.val > node.val) {
            //逆序对中较小的那个节点
            second = node;
            /**
             * 最多只会有2个逆序对,如果first != null 那说明这已经是第二对
             */
            if (first != null) return;
            //将第一个逆序对的较大那个赋值给first
            first = prev;
        }
        prev = node;
    }

    public void recoverTree1(TreeNode root) {
        findWrongNodes(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    private void findWrongNodes(TreeNode root) {
        if (root == null) return;
        findWrongNodes(root.left);
        //出现逆序对
        if (prev != null && prev.val > root.val) {
            //逆序对中较小的那个节点
            second = root;
            /**
             * 最多只会有2个逆序对,如果first != null 那说明这已经是第二对
             */
            if (first != null) return;
            //将第一个逆序对的较大那个赋值给first
            first = prev;
        }
        prev = root;
        findWrongNodes(root.right);
    }
}
