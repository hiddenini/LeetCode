package hf;


/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class _42_接雨水 {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int lastIdx = height.length - 2;
        int[] leftMaxes = new int[height.length];
        /**
         * 使用动态规划计划,每一次只需要拿到前一个的高度,和前一个柱子的左边最大的那个柱子
         */
        for (int i = 1; i <= lastIdx; i++) {
            leftMaxes[i] = Math.max(leftMaxes[i - 1], height[i - 1]);
        }
        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }
        int water = 0;
        /**
         * 遍历每一根柱子,看看每一根柱子上能放多少水
         * 看一根柱子是否能接水,要看min(max(left),max(right))即左边要有比自己高的,右边也要有比自己高的,
         * 能接的水就是min-height[i]
         * 所以第一个和最后一个柱子不能接水,跳过
         */
        for (int i = 1; i <= lastIdx; i++) {
            //求出左边最大,右边最大中的较小值
            int min = Math.min(leftMaxes[i], rightMaxes[i]);
            //左边或者右边没有比自己高的,不能接水
            if (min <= height[i]) continue;
            water += min - height[i];
        }
        return water;
    }

    public int trap1(int[] height) {
        if (height == null || height.length == 0) return 0;
        int lastIdx = height.length - 2;

        int[] rightMaxes = new int[height.length];
        for (int i = lastIdx; i >= 1; i--) {
            rightMaxes[i] = Math.max(rightMaxes[i + 1], height[i + 1]);
        }
        int water = 0, leftMax = 0;
        /**
         * 遍历每一根柱子,看看每一根柱子上能放多少水
         * 看一根柱子是否能接水,要看min(max(left),max(right))即左边要有比自己高的,右边也要有比自己高的,
         * 能接的水就是min-height[i]
         * 所以第一个和最后一个柱子不能接水,跳过
         */
        for (int i = 1; i <= lastIdx; i++) {
            leftMax = Math.max(leftMax, height[i - 1]);
            //求出左边最大,右边最大中的较小值
            int min = Math.min(leftMax, rightMaxes[i]);
            //左边或者右边没有比自己高的,不能接水
            if (min <= height[i]) continue;
            water += min - height[i];
        }
        return water;
    }

    /**
     * 思路是不需要同时找到左边最大的和右边最大的,只需要明确最大的中较小的那个,以及另一边有比较小的那个大或者等于的柱子
     * <p>
     * 因为最后能接的水是由min(max(left),max(right))-height[i]决定的
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0, r = height.length - 1, lowerMax = 0, water = 0;
        while (l < r) {
            //height[l]和height[r] 中较小的那个
            int lower = height[height[l] < height[r] ? l++ : r--];
            //目前为止遇到的最大的lower
            lowerMax = Math.max(lowerMax, lower);
            water += lowerMax - lower;
        }
        return water;
    }

}
