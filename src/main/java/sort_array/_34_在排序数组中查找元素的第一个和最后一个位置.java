package sort_array;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class _34_在排序数组中查找元素的第一个和最后一个位置 {
    public int[] searchRange(int[] nums, int target) {
        int[] ints = {-1, -1};
        int length = nums.length;
        if (length == 0) return ints;

        int firstPosition = findFirstPosition(nums, target);
        if (firstPosition == -1) return ints;
        int lastPosition = findLastPosition(nums, target);
        return new int[]{firstPosition, lastPosition};
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            /**
             * 这里为啥要加1?
             * eg [5，7，2,9,8,8,3] target=8
             *最后一步 left=4 right=5 mid=4 会死循环
             * >>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
             * >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。
             */
            int mid = (left + right + 1) >>> 1;
            if (nums[mid] < target) {
                //说明最后一个target不可能在左边
                left = mid + 1;
            } else if (nums[mid] == target) {
                //说明最后一个target不可能在左边
                left = mid;
            } else {
                //说明最后一个target不可能在右边
                right = mid - 1;
            }
        }

        return left;
    }

    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] < target) {
                //说明左边不可能存在第一个target
                left = mid + 1;
            } else if (nums[mid] == target) {
                //说明右边不可能存在第一个target 一定会在左边 不一定是mid
                right = mid;
            } else {
                //说明左边不可能存在第一个target
                right = mid - 1;
            }
        }
        //target 不一定在数组中存在
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }


    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    /**
     *计算下边界
     */
    int lowerBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //这里需要注意，计算mid
            int mid = left + ((right - left) >> 1);
            if (target <= nums[mid]) {
                //当目标值小于等于nums[mid]时，继续在左区间检索，找到第一个数
                right = mid - 1;

            }else if (target > nums[mid]) {
                //目标值大于nums[mid]时，则在右区间继续检索，找到第一个等于目标值的数
                left = mid + 1;

            }
        }
        return left;
    }

    /**
     *计算上边界
     */
    int upperBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //求mid
            int mid = left + ((right - left) >> 1);
            //移动左指针情况
            if (target >= nums[mid]) {
                left = mid + 1;
                //移动右指针情况
            }else if (target < nums[mid]) {
                right = mid - 1;
            }

        }
        return left;
    }

    public int[] searchRange2(int[] nums, int target) {
        int upper = upperBound(nums,target);
        int low = lowerBound(nums,target);
        //不存在情况
        if (upper < low) {
            return new int[]{-1,-1};
        }
        return new int[]{low,upper};
    }

}
