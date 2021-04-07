package Tree;

/**
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class _108_将有序数组转换为二叉搜索树 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int low, int high) {
        if (low > high) return null;
        int mid = (low + high) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        //递归构建左右子树
        root.left = helper(nums, low, mid - 1);
        root.right = helper(nums, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {
        System.out.println(5 / 2);
        System.out.println(5 >> 1);
    }
}
