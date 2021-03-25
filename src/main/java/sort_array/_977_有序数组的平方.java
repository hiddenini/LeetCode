package sort_array;

/**
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 */
public class _977_有序数组的平方 {
    public int[] sortedSquares(int[] nums) {
        int position = nums.length - 1;
        int[] arr = new int[nums.length];
        for (int i = 0, cur = nums.length - 1; i <= cur; ) {
            if (nums[i] * nums[i] > nums[cur] * nums[cur]) {
                arr[position] = nums[i] * nums[i];
                ++i;
            } else {
                arr[position] = nums[cur] * nums[cur];
                cur--;
            }
            position--;
        }
        return arr;
    }

}
