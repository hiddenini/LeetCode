package sort_array;

public class _000_二分查找 {
    /**
     * https://leetcode-cn.com/problems/search-a-2d-matrix/solution/yi-wen-dai-ni-gao-ding-duo-ge-er-fen-cha-2hl9/
     *
     * int[ ]  nums = {1,3,4,5,6,8,12,14,16}; target = 8
     * <p>
     * 1.计算 mid 时 ，不能使用 （left + right ）/ 2,否则有可能会导致溢出
     * <p>
     * 2.while (left < = right) { } 注意括号内为 left <= right ,而不是 left < right ，我们继续回顾刚才的例子，如果我们设置条件为 left < right 则当我们执行到最后一步时，则我们的 left 和 right 重叠时，则会跳出循环，返回 -1，区间内不存在该元素，但是不是这样的，我们的 left 和 right 此时指向的就是我们的目标元素 ，但是此时 left = right 跳出循环
     * <p>
     * 3.left = mid + 1,right = mid - 1 而不是 left = mid 和 right = mid。我们思考一下这种情况,见下图，当我们的target 元素为 16 时，然后我们此时 left = 7 ，right = 8，mid = left + (right - left) = 7 + (8-7) = 7，那如果设置 left = mid 的话，则会进入死循环，mid 值一直为7 。
     */
    public static int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            //防止溢出 不要写成 mid =（left + right）/ 2
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 递归写法
     *
     * @return
     */
    public static int binarySearch1(int[] nums, int target, int left, int right) {

        if (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                //查找成功
                return mid;
            } else if (nums[mid] > target) {
                //新的区间,左半区间
                return binarySearch1(nums, target, left, mid - 1);
            } else if (nums[mid] < target) {
                //新的区间，右半区间
                return binarySearch1(nums, target, mid + 1, right);
            }
        }
        //不存在返回-1
        return -1;
    }


    /**
     * 找出第一个大于目标元素的索引
     * <p>
     * nums = {1,3,5,5,6,6,8,9,11} 找出第一个大于 5的元素的索引
     */

    public static int lowBound(int[] nums, int target, int left, int right) {
        while (left <= right) {
            //求中间值
            int mid = left + ((right - left) >> 1);
            //大于目标值的情况
            if (nums[mid] > target) {
                //看看是不是当前区间的第一位 ,或者如果当前大于，前面一位小于，返回当前值即可
                if (mid == 0 || nums[mid - 1] <= target) {
                    return mid;
                } else {
                    right = mid - 1;
                }

            } else if (nums[mid] <= target) {
                left = mid + 1;
            }
        }
        //所有元素都小于目标元素
        return -1;

    }

    /**
     * 找出最后一个小于目标元素的索引
     * <p>
     * nums = {1,3,5,5,6,6,8,9,11} target = 7  找出最后一个小于 7的元素的索引
     */

    public static int upperBound(int[] nums, int target, int left, int right) {
        while (left <= right) {

            int mid = left + ((right - left) >> 1);
            //小于目标值
            if (nums[mid] < target) {
                //看看是不是当前区间的最后一位 ,或者如果当前小于，后面一位大于，返回当前值即可
                if (mid == right || nums[mid + 1] >= target) {
                    return mid;
                } else {
                    left = mid + 1;
                }

            } else if (nums[mid] >= target) {
                right = mid - 1;
            }
        }
        //没有查询到的情况
        return -1;
    }

    /**
     * 在不完全有序的数组中 查找目标元素（不含重复元素）
     */
    public int search(int[] nums, int target) {
        //左右指针
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }

            //由于 mid 肯定是在 left 和right 之间
            //所以mid只有两种情况，和 left 在一个数组，同时落在 数组1 或同时在 数组2，或者不在一个数组， left 在数组1，mid 在数组2。
            // 落在同一数组的情况，同时落在数组1 或 数组2
            if (nums[mid] >= nums[left]) {
                //target 落在 left 和 mid 之间，则移动我们的right，完全有序的一个区间内查找
                //target落在mid左边
                if (nums[mid] > target && target >= nums[left]) {
                    right = mid - 1;
                    // target 落在mid右边
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                }
            } else {
                //不落在同一数组的情况，left 在数组1， mid 落在 数组2
                //mid 值落在 数组2
                //有序的一段区间，target 在 mid 和 right 之间
                //target落在mid右边
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                    // 两种情况，target 在left 和 mid 之间
                    //target落在mid左边
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                }
            }

        }
        //没有查找到
        return -1;
    }


    /**
     * 在不完全有序的数组中 查找目标元素（含重复元素）
     */
    public boolean search1(int[] nums, int target) {
        //左右指针
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] == nums[left]) {
                left++;
                continue;
            }

            //由于 mid 肯定是在 left 和right 之间
            //所以mid只有两种情况，和 left 在一个数组，同时落在 数组1 或同时在 数组2，或者不在一个数组， left 在数组1，mid 在数组2。
            // 落在同一数组的情况，同时落在数组1 或 数组2
            if (nums[mid] >= nums[left]) {
                //target 落在 left 和 mid 之间，则移动我们的right，完全有序的一个区间内查找
                //target落在mid左边
                if (nums[mid] > target && target >= nums[left]) {
                    right = mid - 1;
                    // target 落在mid右边
                } else if (target > nums[mid] || target < nums[left]) {
                    left = mid + 1;
                }
            } else {
                //不落在同一数组的情况，left 在数组1， mid 落在 数组2
                //mid 值落在 数组2
                //有序的一段区间，target 在 mid 和 right 之间
                //target落在mid右边
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                    // 两种情况，target 在left 和 mid 之间
                    //target落在mid左边
                } else if (target < nums[mid] || target > nums[right]) {
                    right = mid - 1;
                }
            }

        }
        //没有查找到
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 5, 6, 8, 12, 14, 16};
        System.out.println(binarySearch(nums, 8, 0, nums.length - 1));
        //找到第一个比6大的数
        System.out.println(lowBound(nums, 6, 0, nums.length - 1));
        //找到最后一个比6小的数
        System.out.println(upperBound(nums, 6, 0, nums.length - 1));
    }
}
