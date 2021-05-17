import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 677 键值映射
 */
class MapSum {

    private class Node{
        int val;
        Map<Character,Node> next;

        public Node(int val) {
            this.val = val;
            this.next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for(int i = 0;i < key.length();i++){
            char c = key.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }
        cur.val = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for(int i = 0;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return 0;
            cur = cur.next.get(c);
        }
        return sum(cur);
    }

    private int sum(Node cur){
        int res = cur.val;
        for(char ch : cur.next.keySet())
            res += sum(cur.next.get(ch));
        return res;
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple",3);
        int res1 = mapSum.sum("ap");
        System.out.println(res1);
        mapSum.insert("app",2);
        int res2 = mapSum.sum("ap");
        System.out.println(res2);
    }


//    输入：
//            ["MapSum", "insert", "sum", "insert", "sum"]
//            [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//    输出：
//            [null, null, 3, null, 5]
//
//    解释：
//    MapSum mapSum = new MapSum();
//    mapSum.insert("apple", 3);
//    mapSum.sum("ap");           // return 3 (apple = 3)
//    mapSum.insert("app", 2);
//    mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)



}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */