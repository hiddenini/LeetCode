package sort_array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class _128_最长连续序列 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int result = 0;
        for (int num : nums) {
            if (set.contains(num - 1))
                continue;
            else {
                int curLength = 0;
                while (set.contains(num++)) {
                    curLength++;
                }
                result = Math.max(result, curLength);
            }
        }
        return result;
    }
}
