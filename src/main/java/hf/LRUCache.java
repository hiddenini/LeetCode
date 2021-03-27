package hf;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private Map<Integer, Node> map;
    private int capacity;
    //虚拟头节点
    private Node first;
    //虚拟尾节点
    private Node last;


    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        first = new Node();
        last = new Node();
        first.next = last;
        last.prev = first;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        /**
         *将该节点插入到头节点后面
         */
        //先删除该节点
        removeNode(node);
        //再插入到头节点后面
        addAfterFirst(node);
        return node.value;
    }

    /**
     * 从双向表中删除node节点
     */
    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    /**
     * 将node插入到first节点后面
     */
    private void addAfterFirst(Node node) {
        //先维护node和fist.next
        node.next = first.next;
        first.next.prev = node;

        //再维护first和node,否则先维护了first和node之后,就不能通过fist.next找到之前的那个节点了
        first.next = node;
        node.prev = first;
    }


    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            //存在则覆盖
            node.value = value;
            /**
             * 访问一次就操作一次
             */
            removeNode(node);
            addAfterFirst(node);
        } else {
            //添加新的key value
            if (map.size() == capacity) {
                //淘汰最近最少使用的k -v,就是last的前一个节点last.prev,并且会返回被删除的这个key对应的value
                Node node1 = map.remove(last.prev.key);
                removeNode(node1);
            }
            map.put(key, node = new Node(key, value));
            addAfterFirst(node);
        }
    }

    private static class Node {
        public int key;
        public int value;
        public Node prev;
        public Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {

        }
    }
}