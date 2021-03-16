package stack;

import commom.TreeNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 */
public class _654_最大二叉树 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return findRoot(nums, 0, nums.length);
    }

    /**
     * 找出[l,r) 范围内的根节点
     */
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;
        int maxIdx = l;
        for (int i = l + 1; i < r; i++) {
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = findRoot(nums, l, maxIdx);
        root.right = findRoot(nums, maxIdx + 1, r);
        return root;
    }

    /**
     * 找出每个元素的左边 右边第一个比他大的元素
     * <p>
     * 最大树的每个节点的父节点的索引就是 左边 右边第一个比他大的元素 中的比较小的那个
     */
    public int[] parentIndexes(int nums[]) {
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < nums.length; i++) {
            ris[i] = -1;
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                ris[stack.pop()] = i;
            }
            lis[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        System.out.println(Arrays.toString(lis));
        System.out.println(Arrays.toString(ris));

        int[] pis = new int[nums.length];
        for (int i = 0; i < pis.length; i++) {
            //根节点
            if (lis[i] == -1 && ris[i] == -1) {
                pis[i] = -1;
                continue;
            }

            if (lis[i] == -1) {
                pis[i] = ris[i];
            } else if (ris[i] == -1) {
                pis[i] = lis[i];
            } else if (nums[lis[i]] < nums[ris[i]]) {
                pis[i] = lis[i];
            } else {
                pis[i] = ris[i];
            }

        }
        return pis;
    }

    public static void main(String[] args) {
        _654_最大二叉树 obj = new _654_最大二叉树();
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        int[] ints = obj.parentIndexes(nums);
        System.out.println(Arrays.toString(ints));
    }
}
