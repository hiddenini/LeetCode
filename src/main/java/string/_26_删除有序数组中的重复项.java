package string;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class _26_删除有序数组中的重复项 {
    /**
     * 1.比较 p 和 q 位置的元素是否相等。
     * <p>
     * 如果相等，q 后移 1 位
     * 如果不相等，将 q 位置的元素复制到 p+1 位置上，p 后移一位，q 后移 1 位
     * 重复上述过程，直到 q 等于数组长度。
     * <p>
     * 返回 p + 1，即为新数组长度。
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int i = 0;
        for (int j = 1; j < length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i;
    }

    /**
     * 此时数组中没有重复元素，按照上面的方法
     * <p>
     * 每次比较时 nums[p] 都不等于 nums[q]，因此就会将 q 指向的元素原地复制一遍
     */
    public int removeDuplicates1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    /**
     * 添加一个小判断，当 q - p > 1 时，才进行复制
     * 但是是否小效率更高,判断和赋值操作哪个更耗时?
     */
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        return p + 1;
    }
}
