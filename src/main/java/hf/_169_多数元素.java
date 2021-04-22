package hf;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/majority-element/
 */
public class _169_多数元素 {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    public int majorityElement1(int[] nums) {
        int candidate = nums[0], count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count = 0;
                }
            }
        }
        return candidate;
    }

}
