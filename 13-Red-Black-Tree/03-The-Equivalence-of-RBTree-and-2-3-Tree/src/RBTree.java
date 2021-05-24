/**
 * 红黑树本质也是一颗二分搜索树
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>,V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left,right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size = 0;
    }

    /**
     * 判断节点的颜色
     * @param node
     * @return
     */
    private boolean isRed(Node node){
        if(node == null)
            return BLACK;
        return node.color;
    }



    /**
     * 向红黑树添加新的元素(key, value)
     * @param key
     * @param value
     */

    public void add(K key, V value) {
        root = add(root,key,value);
    }

    private Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else if(key.compareTo(node.key) > 0)
            node.right = add(node.right,key,value);
        else // key.compareTo(node.key) == 0
            node.value = value;
        return node;
    }

    /**
     * 返回以node为根节点的红黑树中，key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        if(node == null)
            return null;
        if(key.compareTo(node.key) == 0)
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else //if(key.compareTo(node.key) > 0)
            return getNode(node.right,key);
    }



    public boolean contains(K key) {
        return getNode(root,key) != null;
    }


    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }


    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");
        node.value = newValue;
    }

    /**
     * 返回以node为根的红黑树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的红黑树中的最小节点
     * 返回删除节点后新的红黑树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从红黑树中删除键值为key的节点
     * @param key
     */

    public V remove(K key){
        Node node = getNode(root,key);
        if(node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除掉以node为根的红黑树中键值为key的节点
     * 返回删除节点后新的红黑树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if(node == null)
            return null;
        if(node.key.compareTo(key) < 0){
            node.left = remove(node.left,key);
            return node;
        }
        else if(node.key.compareTo(key) > 0){
            node.right = remove(node.right,key);
            return node;
        }
        else{  // node.key.compareTo(key) == 0
            if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            else{
                //待删除节点左右子树均不为空的情况
                //找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
                //用这个节点顶替待删除节点的位置
                // node.left != null && node.right != null
                Node rightNode = node.right;
                //后继节点
                Node succsessor = minimum(rightNode);
                succsessor.right = removeMin(rightNode);
                succsessor.left = node.left;
                node.left = null;
                node.right = null;
                return succsessor;
            }
        }
    }


    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }

}
