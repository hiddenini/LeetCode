package sort_array;

import java.util.Random;

/**
 * 觉得快排重要，单独写下
 */
public class _000_快速排序 {


    private static Random random = new Random();

    /**
     * 方式一
     */
    private static void quick(int[] nums, int i, int j) {
        if (j - i < 1) return;
        int begin = i, end = j;
        int pivot = nums[begin];
        while (begin < end) {
            //从右往左走
            while (begin < end) {
                if (pivot < nums[end]) {
                    end--;
                } else {
                    nums[begin] = nums[end];
                    begin++;
                    break;
                }
            }
            //从右往左走
            while (begin < end) {
                if (pivot > nums[begin]) {
                    begin++;
                } else {
                    nums[end] = nums[begin];
                    end--;
                    break;
                }
            }
        }
        nums[begin] = pivot;
        quick(nums, i, begin - 1);
        quick(nums, begin + 1, j);
    }

    /**
     * 方式二
     */
    static void QuickSort(int R[], int lo, int hi) {
        if (hi - lo < 1) return;
        int i = lo, j = hi;
        int temp;
        if (i < j) {
            temp = R[i];
            while (i < j) {
                while (i < j && R[j] >= temp) --j;
                R[i] = R[j];
                while (i < j && R[i] <= temp) ++i;
                R[j] = R[i];
            }
            R[i] = temp;
            QuickSort(R, lo, i - 1);
            QuickSort(R, i + 1, hi);
        }
    }

    /**
     * 方式三 优化 防止出现最坏时间复杂度
     */
    static void QuickSort1(int R[], int lo, int hi) {
        int i = lo, j = hi;
        int temp;
        if (i < j) {
            /**
             *  随机选择一个元素跟begin位置进行（做为轴点）交换是为了避免出现最坏的时间紧复杂度的情况
             * eg 7 6 5 4 3 2 1 如果是选择7作为基准，那么分割出来的子序列是极度不均匀的,会导致O(n^2)
             */
            int randi = random.nextInt(j - i + 1) + i;
            swap(R, i, randi);
            temp = R[i];
            while (i < j) {
                //注意这里不能用>= 因为如果所有的元素都一样的时候如果加上等于也会导致分割出来的子序列是极度不均匀的,会导致O(n^2)
                while (i < j && R[j] >= temp) --j;
                R[i] = R[j];
                //注意这里不能用>= 因为如果所有的元素都一样的时候如果加上等于也会导致分割出来的子序列是极度不均匀的,会导致O(n^2)
                while (i < j && R[i] <= temp) ++i;
                R[j] = R[i];
            }
            R[i] = temp;
            QuickSort1(R, lo, i - 1);
            QuickSort1(R, i + 1, hi);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int begin, int end) {
        if (end - begin < 1) return;

        // 确定轴点位置 O(n)
        int mid = pivotIndex(nums, begin, end);
        // 对子序列进行快速排序
        sort(nums, begin, mid - 1);
        sort(nums, mid + 1, end);
    }

    private static int pivotIndex(int[] nums, int begin, int end) {
        int pivot = nums[begin];
        while (begin < end) {
            while (begin < end) {
                if (pivot < nums[end]) { // 右边元素 > 轴点元素
                    end--;
                } else { // 右边元素 <= 轴点元素
                    nums[begin++] = nums[end];
                    break;
                }
            }
            while (begin < end) {
                if (pivot > nums[begin]) { // 左边元素 < 轴点元素
                    begin++;
                } else { // 左边元素 >= 轴点元素
                    nums[end--] = nums[begin];
                    break;
                }
            }
        }

        // 将轴点元素放入最终的位置
        nums[begin] = pivot;
        // 返回轴点元素的位置
        return begin;
    }


    public static void main(String[] args) {
/*        int a = 1;
        int b = 10;
        int r = (int) (Math.random() * (b - a + 1) + a);
        System.out.println(r);*/
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        //quick(nums, 0, nums.length - 1);
        //QuickSort(nums, 0, nums.length - 1);
        sort(nums);
        //QuickSort1(nums, 0, nums.length - 1);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}

