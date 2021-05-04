package sort_array;

/**
 * 执行流程
 * <p>
 * 不断地将当前序列平均分割成2个子序列
 * <p>
 * 直到不能再分割（序列中只剩1个元素）
 * <p>
 * 不断地将2个子序列合并成一个有序序列
 * <p>
 * 直到最终只剩下1个有序序列
 */
public class _000_归并排序 {


    public int[] sortArray(int[] nums) {
        divide(0, nums.length, nums);
        return nums;
    }

    /**
     * 对[begin,end)内的数据进行归并排序
     * <p>
     * 就是divide
     */
    public void divide(int begin, int end, int[] nums) {
        if (end - begin < 2) return;
        int mid = (begin + end) >> 1;
        divide(begin, mid, nums);
        divide(mid, end, nums);
        merge(begin, mid, end, nums);
    }

    /**
     * 将[begin,mid)和[mid,end)2个有序序列合并成一个有序序列
     */
    public void merge(int begin, int mid, int end, int nums[]) {

        int li = 0, le = mid - begin;
        int ri = mid, re = end;
        //ai是数组中下一个会被赋值的索引
        int ai = begin;

        //备份左边数组  因为排序是在原数组进行的 所以不能直接覆盖 备份其中一份
        /**
         * 其实可以定义一个全局的left 长度是原数组的一半 这样就不用每次都生成一个新的left 但是需要在函数中传递
         */
        int[] left = new int[mid - begin];

        for (int i = li; i < le; i++) {
            left[i] = nums[begin + i];
        }
        /**
         * 左边结束 则提前结束 右边先结束 则将左边剩下的数据直接挪到右边
         */
        while (li < le) {
            /**
             * 右边的索引没越界并且右边的小于左边的 那么将数组右边的数组挪动到数组的最左边
             *
             * 这样不仅保证稳定性 并且右边结束了 即 ri>==re 那么进入else 直接将left挪动nums
             */
            if (ri < re && nums[ri] < left[li]) {
                nums[ai++] = nums[ri++];
            } else {
                nums[ai++] = left[li++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        _000_归并排序 obj = new _000_归并排序();
        obj.sortArray(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
