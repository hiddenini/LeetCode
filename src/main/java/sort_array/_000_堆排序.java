package sort_array;

/**
 * 执行流程
 * 对序列进行原地建堆（heapify）
 * <p>
 * 重复执行以下操作，直到堆的元素数量为
 * <p>
 * 交换堆顶元素与尾元素
 * <p>
 * 堆的元素数量减 1
 * <p>
 * 对 0 位置进行 1 次 siftDown 操作
 */


/**
 * 堆的一个重要性质：任意节点的值总是 ≥（ ≤ ）子节点的值
 * <p>
 * 二叉堆的逻辑结构就是一棵完全二叉树，所以也叫完全二叉堆
 * <p>
 * 鉴于完全二叉树的一些特性，二叉堆的底层（物理结构）一般用数组实现即可
 * <p>
 * 索引 i 的规律（ n 是元素数量）
 * <p>
 * 如果 i = 0 ，它是根节点
 * <p>
 * 如果 i > 0 ，它的父节点的索引为 floor( (i – 1) / 2 ) java除法默认就是向下取整的
 * <p>
 * 如果 2i + 1 ≤ n – 1，它的左子节点的索引为 2i + 1
 * 如果 2i + 1 > n – 1 ，它无左子节点
 * <p>
 * 如果 2i + 2 ≤ n – 1 ，它的右子节点的索引为 2i + 2
 * 如果 2i + 2 > n – 1 ，它无右子节点
 */
public class _000_堆排序 {
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    protected void heapSort(int[] nums) {
        // 原地建堆
        int heapSize = nums.length;
        /**
         * 自下而上的下滤O(n) 将一个数组构建成一个堆(这个堆也是一个数组 只是说数据的顺序逻辑是是一个堆)
         *
         * 用数组表示一个堆 那么最后一个非叶子节点的索引是nums.length>>1-1
         *
         * 第一个叶子节点的索引是nums.length>>1
         *
         * 本质上是先让左右2边形成堆 然后新元素加入之后就相当于在一个堆中删除堆顶元素
         */
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i, nums, heapSize);
        }

        /**
         * 自上而下的上滤O(nlogn) 每有一个元素上滤 当前的所有元素就形成了一个堆 也就是说元素是一个一个的加入到一个堆中
         *
         * 本质是添加一个元素到一个堆中  一般来说从第二个节点开始上滤 因为根节点不需要上滤
         */
/*        for (int i = 1; i < heapSize; i++) {
            siftUp(i, nums);
        }*/

        while (heapSize > 1) {
            // 交换堆顶元素和尾部元素
            swap(0, --heapSize, nums);

            // 对0位置进行siftDown（恢复堆的性质）
            siftDown(0, nums, heapSize);
        }
    }

    /**
     * 下滤 让index位置的元素下滤
     * 1. 用最后一个节点覆盖根节点
     * 2. 删除最后一个节点
     * 3. 循环执行以下操作
     * 如果 node < 最大的子节点
     * ✓ 与最大的子节点交换位置
     * 如果 node ≥ 最大的子节点， 或者 node 没有子节点
     * ✓ 退出循环
     * ◼ 这个过程，叫做下滤（Sift Down），时间复杂度：O(logn)
     */
    private void siftDown(int index, int[] nums, int
            heapSize) {
        Integer element = nums[index];

        //第一个叶子节点的索引是nums.length>>1 索引index<nums.length 就是非叶子节点
        int half = heapSize >> 1;
        while (index < half) { // index必须是非叶子节点 因为叶子节点没有子节点 不需要下滤
            /**
             * 堆是一种完全二叉树 所以只可能存在左节点 或者存在左右节点 不可能只有右节点没有左节点
             */
            // 默认是左边跟父节点比
            int childIndex = (index << 1) + 1;
            Integer child = nums[childIndex];

            int rightIndex = childIndex + 1;
            // 右子节点比左子节点大
            if (rightIndex < heapSize &&
                    cmp(nums[rightIndex], child) > 0) {
                //如果右节点存在并且右节点比左节点要大  那么将child  和childIndex 设置为右节点
                childIndex = rightIndex;
                child = nums[rightIndex];
                //child = nums[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmp(element, child) >= 0) break;

            //将子节点放在index位置
            nums[index] = child;
            //重新设置index 继续往下看
            index = childIndex;
        }
        //和上滤一样减少交换 循环推出才把element放在最终该放的位置 index
        nums[index] = element;
    }

    public void swap(int i, int j, int[] nums) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public int cmp(int one, int two) {
        //升序排序
        return one - two;
        //降序排序
        //return two - one;
    }

    /**
     * 上滤
     * 让index位置的元素上滤
     */
    private void siftUp(int index, int[] nums) {
        int num = nums[index];
/*        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            int parent = nums[parentIndex];
            if (cmp(num, parent) < 0) break;
            swap(index, parentIndex, nums);
            index = parentIndex;
        }*/

        //可以优化 省去很多交换操作
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            int parent = nums[parentIndex];
            if (cmp(num, parent) < 0) break;
            //如果index位置元素大于父节点 那么父节点放在index位置
            nums[index] = parent;
            //将父节点index赋值给index 继续循环 也就是继续向上看是否index位置元素还是大于父节点
            index = parentIndex;
        }
        //循环结束后 index位置就是最终num应该放的位置
        nums[index] = num;

    }

    public static void main(String[] args) {
        _000_堆排序 obj = new _000_堆排序();
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        obj.sortArray(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
