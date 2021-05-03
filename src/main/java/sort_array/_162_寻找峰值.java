package sort_array;


/**
 * https://leetcode-cn.com/problems/find-peak-element/
 */
public class _162_寻找峰值 {
    /**
     * 输出的永远是第一个峰值
     */
    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }
        return nums.length - 1;
    }

    public int findPeakElement2(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    /**
     * 递归二分
     */
    private int search(int[] nums, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = l + (r - l) >> 1;
        if (nums[mid] > nums[mid + 1]) {
            return search(nums, l, mid);
        }
        return search(nums, mid + 1, r);
    }


    /**
     * 迭代二分
     */
    private int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l+r) >> 1;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        _162_寻找峰值 obj = new _162_寻找峰值();
        System.out.println(obj.findPeakElement(new int[]{1, 2, 3, 1, 5, 2}));
    }
}
