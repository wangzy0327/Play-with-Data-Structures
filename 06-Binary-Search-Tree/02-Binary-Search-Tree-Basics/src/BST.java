public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;
        public Node(E e){
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向二分搜索树中添加新的元素e
     * @param e
     */
    public void add(E e){
//        root = add(root,e);
        if(root == null){
            root = new Node(e);
            size++;
        }
        else
            addNoRecursive(root,e);
    }


    /**
     * 以Node 为根的二分搜索树中插入元素e，非递归算法
     * @param node
     * @param e
     */
    private void addNoRecursive(Node node,E e){
        assert node != null;
        Node cur = node;
        while(e.compareTo(cur.e) != 0 ){
            if(e.compareTo(cur.e) < 0 && cur.left != null)
                cur = cur.left;
            else if(e.compareTo(cur.e) > 0 && cur.right != null)
                cur = cur.right;
            else if(e.compareTo(cur.e) < 0 && cur.left == null){
                cur.left = new Node(e);
                size++;
                return ;
            }
            else if(e.compareTo(cur.e) > 0 && cur.right == null){
                cur.right = new Node(e);
                size++;
                return ;
            }
        }
    }

    /**
     * 向以node为根的二分搜索树中插入元素e，递归算法
     * @param node
     * @param e
     */
//    private void add(Node node,E e){
//        if(e.equals(node.e))
//            return;
//        else if(e.compareTo(node.e) < 0 && node.left == null){
//            node.left = new Node(e);
//            size++;
//            return;
//        }else if(e.compareTo(node.e) > 0 && node.right == null){
//            node.right = new Node(e);
//            size++;
//            return;
//        }
//        if(e.compareTo(node.e) < 0)
//            add(node.left,e);
//        else // e.compareTo(node.e) > 0
//            add(node.right,e);
//    }

    /**
     * 改进型 向以node为根的二分搜索树中插入元素e，递归算法
     * 返回插入新节点后二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node,E e){
        if(node == null){
            size++;
            return new Node(e);
        }
        if(e.compareTo(node.e) < 0)
            node.left = add(node.left,e);
        else if(e.compareTo(node.e) > 0) // e.compareTo(node.e) > 0
            node.right = add(node.right,e);
        return node;
    }

    /**
     * 看二分搜索树中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
//        return contains(root,e);
        return containsNoRecursive(root,e);
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e，递归算法
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node,E e){
        if(node == null)
            return false;
        if(e.compareTo(node.e) == 0)
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left,e);
        else  // e.compareTo(node.e) > 0
            return contains(node.right,e);
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e，非递归算法
     * @param node
     * @param e
     * @return
     */
    private boolean containsNoRecursive(Node node,E e){
        if(node == null)
            return false;
        Node cur = node;
        while(e.compareTo(cur.e) != 0 ){
            if(e.compareTo(cur.e) < 0 && cur.left == null)
                return false;
            else if(e.compareTo(cur.e) > 0 && cur.right == null)
                return false;
            else if(e.compareTo(cur.e) < 0 && cur.left != null)
                cur = cur.left;
            else if(e.compareTo(cur.e) > 0 && cur.right != null)
                cur = cur.right;
        }
        return true;
    }

    /**
     * 二分搜索树的前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void preOrder(Node node){
//        if(node == null)
//            return ;
        if(node != null) {
            System.out.println(node.e);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

}
