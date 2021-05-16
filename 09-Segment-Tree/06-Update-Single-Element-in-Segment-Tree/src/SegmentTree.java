public class SegmentTree<E>{
    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for(int i = 0;i < data.length;i++)
            data[i] = arr[i];
        //详情参照segment-array-size.png
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0,0,arr.length - 1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex,int l,int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r - l)/2;
        int leftChildIdx = leftChild(treeIndex);
        int rightChildIdx = rightChild(treeIndex);
        buildSegmentTree(leftChildIdx,l,mid);
        buildSegmentTree(rightChildIdx,mid+1,r);

        tree[treeIndex] = merger.merge(tree[leftChildIdx],tree[rightChildIdx]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    /**
     * 返回区间[queryL,queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL,int queryR){
        if(queryL < 0 || queryR > data.length ||
                queryL > queryR || queryR < 0 || queryL > data.length)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0,0,data.length - 1,queryL,queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
     * @return
     */
    private E query(int treeIndex,int l,int r,int queryL,int queryR){
        if(l == queryL && r == queryR)
            return tree[treeIndex];

        int leftChildInx = leftChild(treeIndex);
        int rightChildInx = rightChild(treeIndex);
        int mid = l + (r - l)/2;

        if(queryR <= mid){
            return query(leftChildInx,l,mid,queryL,queryR);
        }else if(queryL > mid){
            return query(rightChildInx,mid+1,r,queryL,queryR);
        }

        E leftRes = query(leftChildInx,l,mid,queryL,mid);
        E rightRes = query(rightChildInx,mid+1,r,mid + 1,queryR);
        return merger.merge(leftRes,rightRes);
    }

    /**
     * 将index位置的值，更新为e
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0,0,data.length - 1,index,e);
    }

    /**
     * 在以treeIndex为根的线段树中更新index的值为e
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex,int l,int r,int index,E e){
        if(l == r){
            tree[treeIndex] = e;
            return ;
        }
        int mid = l + (r - l)/2;
        int leftChildIdx = leftChild(treeIndex);
        int rightChildIdx = rightChild(treeIndex);
        if(index >= mid + 1)
            set(rightChildIdx,mid + 1,r,index,e);
        else // index <= mid
            set(leftChildIdx,l,mid,index,e);

        tree[treeIndex] = merger.merge(tree[leftChildIdx],tree[rightChildIdx]);

    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0 ; i < tree.length ; i ++){
            if(tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if(i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}
