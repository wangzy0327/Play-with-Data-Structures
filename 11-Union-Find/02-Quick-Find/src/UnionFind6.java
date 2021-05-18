/**
 * 第六版的Union-Find 使用一个数组构建一棵指向父节点的树
 *
 * 采用递归来压缩树的层数为2，会有额外的开销；
 * 对比第五版，第五版在多次调用isConnected时，会进一步压缩所以性能可能会比第六版要好
 *
 * 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不在是树的层数值
 *  这也是我们的rank不叫height或者depth的原因, 他只是作为比较的一个标准
 *
 */
public class UnionFind6 implements UF{

    // parent[i]表示一个元素所指向的父节点
    private int[] parent;
    // rank[i]表示以i为根的集合所表示的树的层数
    private int[] rank;

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for(int i = 0;i < size;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    /**
     * 查看元素p和元素q是否所属一个集合
     * O(h)复杂度，h为树的高度
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查找元素p对应的集合编号
     * @param p
     * @return
     */
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // path compression 2 , 递归算法
        if(p != parent[p])
            parent[p] = find(parent[p]);

        return parent[p];
    }

    /**
     * 合并元素p和q所属的集合
     * O(h)复杂度，h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot)
            return;

        // 根据两个元素所在树的rank不同判断合并方向
        // 将rank低的集合合并到rank高的集合上
        if(rank[pRoot] < rank[qRoot])
            parent[pRoot] = qRoot;
        else if(rank[pRoot] > rank[qRoot])
            parent[qRoot] = pRoot;
        else{ // rank[pRoot] == rank[qRoot]
            parent[pRoot] = qRoot;
            //此时维护rank的值
            rank[qRoot] += 1;
        }

    }
}
