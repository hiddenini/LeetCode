package hf;

public class _1206_设计跳表 {
    /**
     * 最大层数,类似redis
     */
    private static final int MAX_LEVEL = 32;

    /**
     * 每个新节点的层数,随机决定，系数取redis中的0.25
     */
    private static final double P = 0.25;

    //有效层数 第0层，第1层...
    private int level;

    /**
     * 类似头节点，不存放K-V
     */
    private Node first;

    private int size;

    public _1206_设计跳表() {
        first = new Node(-1, MAX_LEVEL);
        first.nexts = new Node[MAX_LEVEL];
    }

    /**
     * ① 从顶层链表的首元素开始，从左往右搜索，直至找到一个大于或等于目标的元素，或者到达当前层链表的尾部
     * ② 如果该元素等于目标元素，则表明该元素已被找到
     * ③ 如果该元素大于目标元素或已到达链表的尾部，则退回到当前层的前一个元素，然后转入下一层进行搜索
     */

    //返回target是否存在于跳表中。
    public boolean search(int target) {
        Node<Integer> node = first;
        for (int i = level; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(target, node.nexts[i].value)) > 0) {
                node = node.nexts[i];
            }
            //如果存在则直接结束
            if (cmp == 0) {
                return true;
            }
        }
        return false;
    }

    //插入一个元素到跳表
    public void add(int num) {
        //待插入节点的每一层的前驱
        Node<Integer>[] prevs = new Node[level];
        Node<Integer> node = first;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(num, node.nexts[i].value)) > 0) {
                node = node.nexts[i];
            }
            //如果存在则直接结束 leetCode上要求可以有同样的数据 那么这里直接往下走就好
/*            if (cmp == 0) {
                return;
            }*/
            //走到这里的node 意味着会往下走一层
            prevs[i] = node;
        }
        int newLevel = randomLevel();
        //否则添加新的节点
        Node newNode = new Node(num, newLevel);

        //设置前驱和后继
        for (int i = 0; i < newLevel; i++) {
            //如果新节点随机得到的层数大于现有的有效层数,那么直接将头节点指向新节点,头节点初始化时level=MAX_LEVEL
            if (i >= level) {
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }

        }
        //节点数量加1
        size++;
        //设置有效层数
        level = Math.max(level, newLevel);
    }

    //在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可
    public boolean erase(int num) {
        Node<Integer>[] prevs = new Node[level];
        Node<Integer> node = first;
        boolean exist = false;
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(num, node.nexts[i].value)) > 0) {
                node = node.nexts[i];
            }
            //如果存在 继续往下走
            if (cmp == 0) {
                exist = true;
            }
            //走到这里的node 意味着会往下走一层
            prevs[i] = node;
        }
        if (!exist) return false;
        size--;
        //能来到这里说明node是需要删除的节点第0层的前驱
        Node removedNode = node.nexts[0];
        //设置后继
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prevs[i].nexts[i] = removedNode.nexts[i];
        }

        //更新有效层数
        int newLevel = level;
        //head从最高层开始找下一个节点 直到找到第一个不为空的 那么此时的level就是删除之后的level
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }
        return true;
    }


    private static class Node<Integer> {
        Integer value;
        int level;
        //跳表可能有多个next,层次
        private Node<Integer>[] nexts;

        public Node(Integer value, int level) {
            this.value = value;
            nexts = new Node[level];
        }

        @Override
        public String toString() {
            return value + "_" + nexts.length;
        }

    }


    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        _1206_设计跳表 obj = new _1206_设计跳表();
        obj.add(9);
        obj.add(9);
        System.out.println(obj.search(9));
        System.out.println(obj.search(9));
        System.out.println(obj.erase(9));
        System.out.println(obj.search(9));
        System.out.println(obj.erase(9));
        obj.print();
    }


    private int compare(Integer n1, Integer n2) {
        return Integer.compare(n1, n2);
    }

    void print() {
        Node node = first;
        while (node != null) {
            node = node.nexts[0];
            if (node != null) System.out.println(node.value);
        }
    }

    private void keyCheck(int num) {

    }
}
