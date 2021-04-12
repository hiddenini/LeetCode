package sort_array;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class _215_数组中的第K个最大元素 {
    /**
     *暴力法
     */
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - k];
    }

    /**
     * 快排
     */
    public int findKthLargest1(int[] nums, int k) {

        return 0;
    }

    /**
     * 使用java自带的堆
     */
    public int findKthLargest2(int[] nums, int k) {
        //构建一个最小堆
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> {
            return n1 - n2;
        });
        for (int num : nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.poll();
    }


}
