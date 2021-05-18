/**
 * Union Find
 */
public interface UF {

    /**
     * 元素大小
     * @return
     */
    int getSize();

    /**
     * 判断两个集合是否相连（索引）
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p,int q);

    /**
     * 求两个集合的并
     * @param p
     * @param q
     */
    void unionElements(int p,int q);
}
