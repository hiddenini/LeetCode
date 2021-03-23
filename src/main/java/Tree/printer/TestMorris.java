package Tree.printer;

import java.util.Random;

/**
 * 二叉树的Morris遍历 O(n) O(1)
 */
public class TestMorris extends BST<Integer> {


    private static class MorrisTree extends BST<Integer> {
        /**
         * Morris中序遍历
         */
        public void inOrder() {
            inOrder(root);
  /*          Node<Integer> node = root;
            while (node != null) {
                if (node.left != null) {
                    //找到前驱节点 左子树一直往右走
                    Node<Integer> pred = node.left;
                    while (pred.right != null && pred.right != node) {
                        pred = pred.right;
                    }

                    if (pred.right == null) {
                        pred.right = node;
                        node = node.left;
                    } else {//  pred.right = node;
                        System.out.print(node.element + " ");
                        pred.right = null;
                        node = node.right;
                    }

                } else {
                    System.out.print(node.element + " ");
                    node = node.right;
                }
            }*/

        }

        /***
         * 递归的中序遍历
         * @param root
         */
        private void inOrder(Node<Integer> root) {
            if (root == null) return;

            inOrder(root.left);
            System.out.println(root.element);
            inOrder(root.right);
        }
    }


    public static void main(String[] args) {
        MorrisTree tree = new MorrisTree();
        for (int i = 0; i < 10; i++) {
            tree.add(new Random().nextInt(100));
        }
        BinaryTrees.println(tree);
        tree.inOrder();
        System.out.println("---------");
        BinaryTrees.println(tree);
    }
}
