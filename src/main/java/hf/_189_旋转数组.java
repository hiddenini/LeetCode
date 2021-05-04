package hf;

/**
 * https://leetcode-cn.com/problems/rotate-array/
 */
public class _189_旋转数组 {
    /**
     * 暴力法
     */
    public void rotate1(int[] nums, int k) {
        int length = nums.length;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[(i + k) % length] = nums[i];
        }
        System.arraycopy(arr, 0, nums, 0, length);
    }

    /**
     * 先将所有元素翻转，这样尾部的 k\bmod nkmodn 个元素就被移至数组头部
     * 然后我们再翻转 [0,kmodn−1] 区间的元素和 [kmodn,n−1] 区间的元素
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * 翻转数组中begin到start位置的元素
     */
    void reverse(int[] nums, int begin, int end) {
        while (begin < end) {
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
            begin++;
            end--;
        }
    }
}
