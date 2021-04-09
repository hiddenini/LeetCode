package Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 */
public class _106_从中序与后序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null) return null;
        int postLen = postorder.length;
        int inLen = inorder.length;
        if (postLen == 0 || inLen == 0 || postLen != inLen) return null;
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * 遍历中序数组,将值和index存到map中
         */
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postLen - 1, map, 0, inLen - 1);
    }

    /**
     * 和105基本一致,就是计算下标的时候要根据后续遍历的特点来
     */
    private TreeNode buildTree(int[] postOrder, int postLeft, int postRight, Map<Integer, Integer> map,
                               int inLeft, int inRight) {
        if (postLeft > postRight || inLeft > inRight) return null;
        int rootVal = postOrder[postRight];
        TreeNode root = new TreeNode(rootVal);
        /**
         * 得到根节点在中序数组中的index 通过pIndex可以算出前序遍历中的左子树的区间 右子树的区间,然后再递归生成
         */
        Integer pIndex = map.get(rootVal);
        /**
         * 递归生成左子树，右子树
         */
        root.left = buildTree(postOrder, postLeft, pIndex - inRight + postRight - 1, map, inLeft, pIndex - 1);
        root.right = buildTree(postOrder, pIndex - inRight + postRight, postRight - 1, map, pIndex + 1, inRight);
        return root;
    }
}
