package linkedList;

/**
 * @author xz
 * @date 2020/2/15 12:23
 **/


/**
 * ◼https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 */
public class L_237_delete {

    public void deleteNode(ListNode  node) {
        ListNode next=node.next;
       node.val=next.val;
       node.next=next.next;
    }
}
