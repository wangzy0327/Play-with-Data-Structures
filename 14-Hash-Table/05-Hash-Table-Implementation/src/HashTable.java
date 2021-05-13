import java.util.TreeMap;

public class HashTable<K,V> {
    private TreeMap<K,V>[] hashtable;
    private int M;
    private int size;

    public HashTable(int M){
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0;i < M;i++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTable(){
        this(97);
    }

    public int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key,V value){
        TreeMap<K,V> treeMap = hashtable[hash(key)];
        if(treeMap.containsKey(key))
            treeMap.put(key,value);
        else{
            treeMap.put(key,value);
            size++;
        }
    }

    public V remove(K key){
        TreeMap<K,V> treeMap = hashtable[hash(key)];
        V ret = null;
        if(treeMap.containsKey(key)) {
            ret = treeMap.remove(key);
            size--;
        }
        return ret;
    }

    public void set(K key,V value){
        TreeMap<K,V> treeMap = hashtable[hash(key)];
        if(!treeMap.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");
        treeMap.put(key,value);
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        TreeMap<K,V> treeMap = hashtable[hash(key)];
        V ret = null;
        if(treeMap.containsKey(key))
            ret = treeMap.get(key);
        return ret;
    }
}
