public class MaxHeap<E extends Comparable> {

    private Array<E> data;

    public MaxHeap(int capactity){
        data = new Array(capactity);
    }

    public MaxHeap(){
        data = new Array();
    }

    /**
     * 返回堆中元素的个数
     * @return
     */
    public int size(){
        return data.getSize();
    }

    /**
     * 返回堆中是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1)/2;
    }

    /**
     * 返回完全二叉树中的数组表示中，一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树中的数组表示中，一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    private void shiftUp(int k){
        while(k != 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    /**
     * 查看堆中的最大元素
     * @return
     */
    public E findMax(){
        if(isEmpty())
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     * @return
     */
    public E extractMax(){
        E ret = findMax();
        data.swap(0,data.getSize() - 1);
        data.removeLast();
        shiftDown(0);
        return ret;
    }

    private void shiftDown(int k){
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            //data[j] 是 leftChild 和 rightChild 中的最大值
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            if(data.get(k).compareTo(data.get(j)) < 0){
                data.swap(k,j);
                k = j;
            }else{
                break;
            }
        }
    }

}
