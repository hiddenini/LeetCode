package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/ 翻转二叉树
 */
public class L_226invertTree_invertTree {
    /**
     * 前序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root==null) return  root;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 后序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root==null) return  root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        return root;
    }

    /**
     * 中序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root==null) return  root;
        invertTree(root.left);
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        //注意这里应该是left不是right，因为上面已经将左右交换了
        invertTree(root.left);
        return root;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if(root==null) return root;
        Queue<TreeNode> queue=new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp=node.left;
            node.left=node.right;
            node.right=temp;
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
