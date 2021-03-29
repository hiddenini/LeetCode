package hf;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class _11_盛最多水的容器 {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                water = Math.max(water, (r - l) * height[l]);
                //移动较矮的那根柱子
                l++;
            } else {
                water = Math.max(water, (r - l) * height[r]);
                //移动较矮的那根柱子
                r--;
            }
        }
        return water;
    }

    public int maxArea1(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            int minH = (height[l] < height[r]) ? height[l++] : height[r--];
            water = Math.max(water, minH * (r - l + 1));

        }
        return water;
    }

    public int maxArea2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int l = 0, r = height.length - 1, water = 0;
        while (l < r) {
            if (height[l] <= height[r]) {
                int minH = height[l];
                water = Math.max(water, (r - l) * minH);
                //移动较矮的那根柱子,如果移动到的柱子比之前的较矮的柱子还要矮,那么直接跳过
                //l++;
                while (l < r && height[l] <= minH) l++;
            } else {
                int minH = height[r];
                water = Math.max(water, (r - l) * minH);
                //移动较矮的那根柱子,如果移动到的柱子比之前的较矮的柱子还要矮,那么直接跳过
                //r--;
                while (l < r && height[r] <= minH) r--;
            }
        }
        return water;
    }
}
