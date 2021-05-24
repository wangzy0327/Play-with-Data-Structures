import java.util.*;
import java.util.LinkedList;

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

    /**
     * 二分搜索树的非递归前序遍历
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        if(root != null)
            stack.push(root);
        while( !stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);

            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 二分搜索树的非递归中序遍历
     */
    public void inOrderNR(){
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.e);
            cur = cur.right;
        }
    }

    /**
     * 二分搜索树的非递归中序遍历
     * 模拟系统栈
     */
    public void inOrderNR2(){
        if(root == null)
            return ;
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.println(cur.e);
                cur = cur.right;
            }
        }
    }

    /**
     * 二分搜索树的非递归后序遍历
     * 经典后序遍历
     * 后序遍历不同的是，需要经过两次根节点来判断 是否 该打印该节点，对根节点的操作需要出栈才能获取它的子节点，需要二次入栈
     * 需要有标记位来判断是第几次经过该节点，第二次的时候打印输出节点信息
     * Using a tag to record whether the node has been visited
     */
    private class TagNode{
        Node node;
        boolean isFirst;
        TagNode(Node node){
            this.node = node;
            this.isFirst = false;
        }
    };
    /**
     * 二分搜索树的非递归后序遍历
     * 经典后序遍历算法  需要借助 额外的标记位来判断
     */
    public void postOrderNR(){
        if(root == null) {
            return ;
        }
        Stack<TagNode> stack = new Stack<>();
        Node cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(new TagNode(cur));
                cur = cur.left;
            }else{
                TagNode tagNode = stack.pop();
                cur = tagNode.node;
                if(tagNode.isFirst == false){
                    tagNode.isFirst = true;
                    stack.push(tagNode);
                    cur = cur.right;
                }else{
                    System.out.println(tagNode.node.e);
                    cur = null;
                }
            }
        }

    }

    /**
     * 二分搜索树的非递归后序遍历
     * Using two stacks, Reverse Preorder Traversal!
     */
    public void postOrderNR1(){
        Stack<Node> stack = new Stack<>();
        Stack<Node> output = new Stack<>();
        if(root == null)
            return ;
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            output.push(cur);
            if(cur.left != null)
                stack.push(cur.left);
            if(cur.right != null)
                stack.push(cur.right);
        }
        while(!output.isEmpty()){
            Node cur = output.pop();
            System.out.println(cur.e);
        }
    }

    /**
     * 二分搜索树的非递归后序遍历
     * 后序遍历不同的是，需要经过两次根节点来判断 是否 该打印该节点，对根节点的操作需要出栈才能获取它的子节点，需要二次入栈
     * 因为根节点会经过两次，在第二次出栈的时候才进行访问
     * output只存放要打印的节点
     * 模拟系统栈
     */
    public void postOrderNR2(){
        LinkedList<Node> output = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        if(root == null)
            return ;
        Node cur = root;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                output.push(cur);
                cur = cur.right;
            }else{
                cur = stack.pop();
                cur = cur.left;
            }
        }
        while(!output.isEmpty()){
            Node p = output.pop();
            System.out.println(p.e);
        }
    }


    /**
     * 二分搜索树的中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    /**
     * 中序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void inOrder(Node node){
//        if(node == null)
//            return ;
        if(node != null) {
            inOrder(node.left);
            System.out.println(node.e);
            inOrder(node.right);
        }
    }

    /**
     * 二分搜索树的后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    /**
     * 后序遍历以node为根的二分搜索树，递归算法
     * @param node
     */
    private void postOrder(Node node){
//        if(node == null)
//            return ;
        if(node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null)
                q.add(cur.left);
            if(cur.right != null)
                q.add(cur.right);
        }
    }

    /**
     * 找寻二分搜索树中的最小元素
     * @return
     */
    public E minimum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");
        return minimum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值的所在节点
     * @param node
     * @return
     */
    private Node minimum(Node node){
        if(node.left == null)
            return node;
        return minimum(node.left);
    }

    /**
     * 找寻二分搜索树中的最小元素
     * 非递归
     * @return
     */
    public E minimumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");
        Node node = root;
        while(node.left != null){
            node = node.left;
        }
        return node.e;
    }

    /**
     * 找寻二分搜索树中的最大元素
     * @return
     */
    public E maxinum(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");
        return maxinum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值的所在节点
     * @param node
     * @return
     */
    private Node maxinum(Node node){
        if(node.right == null)
            return node;
        return maxinum(node.right);
    }

    /**
     * 找寻二分搜索树中的最大元素
     * 非递归
     * @return
     */
    public E maxinumNR(){
        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");
        Node node = root;
        while(node.right != null){
            node = node.right;
        }
        return node.e;
    }

    /**
     * 从二分搜索树中删除最小值所在节点，返回最小值
     * @return
     */
    public E removeMin(){
        E ret = minimum();
        root = removeMin(root);
        return ret;
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
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最小值所在节点，返回最大值
     * @return
     */
    public E removeMax(){
        E ret = maxinum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node){
        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除元素为e的节点
     * @param e
     */
    public void remove(E e){
        root = remove(root,e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点，递归算法
     * 返回删除节点后新的二分搜索树的根
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node,E e){
        if(node == null)
            return null;
        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left,e);
            return node;
        }else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right,e);
            return node;
        }else{  // e == node.e
            //待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除节点左右子树均不为空的情况
            //找到比待删除节点大的最小节点，即将删除节点右子树的最小节点
            //用这个几点顶替待删除节点的位置
            Node succsessor = minimum(node.right);
            succsessor.right = removeMin(node.right);
            succsessor.left = node.left;

            node.left = node.right = null;
            return succsessor;
        }
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    /**
     * 生成以node为根节点，深度为depth的描述二叉树的字符串
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node,int depth,StringBuilder res){
        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return ;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left,depth + 1,res);
        generateBSTString(node.right,depth + 1,res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0;i < depth;i++)
            res.append("--");
        return res.toString();
    }

}
