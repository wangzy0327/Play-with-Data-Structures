import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>,V> implements Map<K,V>{

    private class Node{
        private K key;
        private V value;
        private Node left,right;
        public int height;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断该二叉树是否是一棵二分搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);
        for(int i = 1;i < keys.size();i++)
            if(keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    /**
     * 二分搜索树 中序遍历 是有序的
     * @param node
     * @param keys
     */
    private void inOrder(Node node,ArrayList<K> keys){
        if(node == null)
            return ;
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 判断以Node为根的二叉树是否是一颗平衡二叉树，递归算法
     * @param node
     * @return
     */
    private boolean isBalanced(Node node){
        if(node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 获得节点node的高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node == null)
            return 0;
        return node.height;
    }

    /**
     * 获得节点node的平衡因子
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    /**
     * 右旋
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;

        //向右旋转
        y.left = x.right;
        x.right = y;


        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;

        return x;

    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    /**
     * 左旋
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;

        //向左旋转过程
        y.right = x.left;
        x.left = y;

        //更新height
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;

        return x;


    }

    /**
     * 向二分搜索树中添加新的元素(key, value)
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root,key,value);
    }

    /**
     * 向以node为根的二分搜索树中插入元素(key, value)，递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }
        if(node.key.equals(key))
            node.value = value;
        else if(key.compareTo(node.key) < 0)
            node.left = add(node.left,key,value);
        else //  key.compareTo(node.key) > 0
            node.right = add(node.right,key,value);

        //更新height
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);
//        if(Math.abs(getBalanceFactor(node)) > 1){
//            //TODO
//            System.out.println("unbalances : "+balanceFactor);
//        }
        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
            //RR
        }else if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        //LR
        else if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            Node x = leftRotate(node.left);
            node.left = x;
            return rightRotate(node);
        }
        //RL
        else if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            Node x = rightRotate(node.right);
            node.right = x;
            return leftRotate(node);
        }
        return node;
    }


    /**
     *从二分搜索树中删除键为key的节点
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root,key);
        if(node != null){
            root = remove(root,key);
            return node.value;
        }
        return null;
    }

    /**
     * 向以node为根的二分搜索树中删除元素key，递归算法
     * 返回删除节点后二分搜索树的根
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node,K key){
        if(node == null)
            return null;
        Node retNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left,key);
            retNode = node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right,key);
            retNode = node;
        }
        else {//key.equals(node.key)
            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
                // 待删除节点右子树为空的情况
            }else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }else{
                // 待删除节点左右子树均不为空的情况

                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
//                successor.right = removeMin(node.right);
                successor.right = remove(node.right,successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }
        if(retNode == null)
            return null;

//更新height
        retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right)) + 1;

        int balanceFactor = getBalanceFactor(retNode);
//        if(Math.abs(getBalanceFactor(node)) > 1){
//            //TODO
//            System.out.println("unbalances : "+balanceFactor);
//        }
        //平衡维护
        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(retNode);
            //RR
        }
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(retNode);
        }
        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            Node x = leftRotate(retNode.left);
            retNode.left = x;
            return rightRotate(retNode);
        }
        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            Node x = rightRotate(retNode.right);
            retNode.right = x;
            return leftRotate(retNode);
        }
        return retNode;

    }




    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root,key) != null;
    }

    /**
     * 返回以node为根节点的二分搜索树中，key所在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node,K key){
        if(node == null)
            return null;
        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left,key);
        else
            //key.compareTo(node.key) > 0
            return getNode(node.right,key);
    }

    @Override
    public V get(K key) {
        Node node = getNode(root,key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root,key);
        if(node == null)
            throw new IllegalArgumentException(key+" doesn't exist!");
        node.value = newValue;
    }


    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));


            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

//            for(String word: words){
//                map.remove(word);
//                if(!map.isBST() || !map.isBalanced())
//                    throw new RuntimeException();
//            }

        }

//        Pride and Prejudice
//        Total words: 125901
//        Total different words: 6529
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        is BST : true
//        is Balanced : true

        System.out.println();
    }

}
