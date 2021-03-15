package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 */
public class _239_滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;
        /**
         * 有k-1个元素不能作为滑动窗口的第一个元素,那么剩下的滑动窗口的数量就是nums.length - (k - 1)
         */
        int[] maxes = new int[nums.length - (k - 1)];
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            //只要nums[队尾]<=nums[i]就删除队尾
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            //将i加到队尾
            deque.offerLast(i);

            //检查窗口的索引是否合法
            int w = i - (k - 1);
            if (w < 0) continue;

            //检查对头的合法性
            if (deque.peekFirst() < w) {
                //队头不在滑动窗口内,删除队头
                deque.pollFirst();
            }

            //设置窗口的最大值
            maxes[w] = nums[deque.peekFirst()];

        }

        return maxes;
    }
}
