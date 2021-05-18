import java.util.TreeSet;

/**
 * leetcode 547 省份数量
 */
public class Solution {

    private class UnionFind{
        private int[] parent;
        private int[] rank;

        public UnionFind(int size){
            parent = new int[size];
            for(int i = 0;i < parent.length;i++)
                parent[i] = i;

            rank = new int[size];
            for(int i = 0;i < rank.length;i++)
                rank[i] = 1;
        }

        public boolean isConnected(int p,int q){
            return find(p) == find(q);
        }

        private int find(int p){
            if(p < 0 || p >= parent.length)
                throw new IllegalArgumentException("p is out of bound.");

            while(p != parent[p]){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void unionElements(int p,int q){
            int pRoot = find(p);
            int qRoot = find(q);

            if(pRoot == qRoot)
                return;

            if(rank[pRoot] < rank[qRoot])
                parent[pRoot] = qRoot;
            else if(rank[pRoot] > rank[qRoot])
                parent[qRoot] = pRoot;
            else{
                parent[pRoot] = qRoot;
                rank[qRoot] += 1;
            }
        }

    }

    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null ||isConnected.length == 0)
            return 0;
        int size = isConnected.length;
        UnionFind uf = new UnionFind(size);
        //初始化 每个 城市 都 不相连；所以初始化集合数量就是 二维数组的长度
        int ret = isConnected.length;
        for(int i = 0;i < isConnected.length;i++){
            for(int j = i;j < isConnected[i].length;j++){
                //当 并查集中 查到二者并不相连时 且 二者之间有连接且不是自身时，将集合数量减一
                if(i != j && isConnected[i][j] == 1 && !uf.isConnected(i,j)){
                    uf.unionElements(i,j);
                    ret -= 1;
                }
            }
        }
        return ret;
    }


    public int findCircleNum2(int[][] M) {

        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0 ; i < n ; i ++)
            for(int j = 0 ; j < i ; j ++)
                if(M[i][j] == 1)
                    uf.unionElements(i, j);

        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0 ; i < n ; i ++)
            set.add(uf.find(i));
        return set.size();
    }

}
