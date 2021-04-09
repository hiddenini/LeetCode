package Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class _105_从前序与中序遍历序列构造二叉树 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen == 0 || inLen == 0 || preLen != inLen) return null;
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * 遍历中序数组,将值和index存到map中
         */
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, Map<Integer, Integer> map,
                               int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) return null;
        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        /**
         * 得到根节点在中序数组中的index 通过pIndex可以算出前序遍历中的左子树的区间 右子树的区间,然后再递归生成
         */
        Integer pIndex = map.get(rootVal);
        /**
         * 递归生成左子树，右子树
         */
        root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft, map, inLeft, pIndex - 1);
        root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight, map, pIndex + 1, inRight);
        return root;
    }
}
