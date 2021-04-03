package Tree;

import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 */
public class _230_二叉搜索树中第K小的元素 {
    public static int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = inOrderTraversal(root, new ArrayList<>());
        return nums.get(k - 1);
    }

    /**
     * 中序遍历得到升序集合,获取其k-1个索引的元素即可
     */
    public static ArrayList<Integer> inOrderTraversal(TreeNode node, ArrayList<Integer> arr) {
        if (node == null) return new ArrayList<>();
        inOrderTraversal(node.left, arr);

        //System.out.println(node.val);
        arr.add(node.val);

        inOrderTraversal(node.right, arr);
        return arr;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(kthSmallest(root, 3));
    }
}
