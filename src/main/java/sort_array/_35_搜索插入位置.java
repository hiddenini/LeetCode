package sort_array;

/**
 * https://leetcode-cn.com/problems/search-insert-position/
 */
public class _35_搜索插入位置 {
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            //防止溢出 不要写成 mid =（left + right）/ 2
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        _35_搜索插入位置 obj = new _35_搜索插入位置();
        int nums[] = new int[]{1, 3, 5, 6};
        System.out.println(obj.searchInsert(nums, 0));
    }
}
