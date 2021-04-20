package sort_array;

/**
 * https://leetcode-cn.com/problems/remove-element/
 */
public class _27_移除元素 {
    /**
     * 如果右指针指向的元素不等于 \textit{val}val，它一定是输出数组的一个元素
     * <p>
     * 我们就将右指针指向的元素复制到左指针位置，然后将左右指针同时右移；
     * <p>
     * 如果右指针指向的元素等于 \textit{val}val，它不能在输出数组里，此时左指针不动，右指针右移一位。
     *
     * right 用来遍历
     * left指向下一个可以放元素的位置
     */
    public int removeElement(int[] nums, int val) {
        int length = nums.length;
        int left = 0;
        for (int right = 0; right < length; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public int removeElement1(int[] nums, int val) {
        int right = nums.length;
        int left = 0;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1];
                right--;
            } else {
                left++;
            }
        }
        return left;
    }


}
