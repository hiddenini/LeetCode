package sort_array;

/**
 *https://leetcode-cn.com/problems/sort-colors/
 */
public class _75_颜色分类 {
    public void sortColors(int[] nums) {
        //红色指针 扫描元素
        int i = 0;
        //0存放的位置,绿色指针
        int l = 0;
        //2存放的位置,紫色指针
        int r = nums.length - 1;
        while (i<= r) {
            int v = nums[i];
            if (v == 0) {
                swap(nums, i, l);
                l++;
                i++;
            } else if (v == 1) {
                i++;
            } else {
                swap(nums, i, r);
                r--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
