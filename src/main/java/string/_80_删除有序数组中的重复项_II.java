package string;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 */
public class _80_删除有序数组中的重复项_II {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length <= 2) return length;
        int p = 2;
        int q = 2;
        while (q < length) {
            if (nums[p - 2] != nums[q]) {
                nums[p] = nums[q];
                p++;
            }
            q++;
        }
        return p;
    }
}

