package string;

import Tree.printer.BinaryTreeInfo;
import Tree.printer.BinaryTrees;
import commom.TreeNode;

/**
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 */
public class _572_另一个树的子树 {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) return false;
        return postSerialize(s).contains(postSerialize(t));
    }

    private String postSerialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postSerialize(root, sb);
        return sb.toString();

    }


    private void postSerialize(TreeNode node, StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            postSerialize(node.left, sb);
        }


        if (node.right == null) {
            sb.append("#!");
        } else {
            postSerialize(node.right, sb);
        }
        sb.append(node.val).append("!");

    }

    public static void main(String[] args) {
        final TreeNode root = new TreeNode(3);
        TreeNode left = root.left = new TreeNode(4);
        left.left = new TreeNode(1);
        left.right = new TreeNode(2);
        root.right = new TreeNode(5);
        BinaryTrees.print(new BinaryTreeInfo() {
            @Override
            public Object root() {
                return root;
            }

            @Override
            public Object left(Object node) {
                return ((TreeNode) node).left;
            }

            @Override
            public Object right(Object node) {
                return ((TreeNode) node).right;
            }

            @Override
            public Object string(Object node) {
                return ((TreeNode) node).val;
            }
        });
        System.out.println();
        _572_另一个树的子树 o = new _572_另一个树的子树();
        System.out.println(o.postSerialize(root));
    }

}
