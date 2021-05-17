import java.util.HashMap;
import java.util.Map;

public class Trie2 {
    private class Node{
        boolean isWord;
        Map<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie2(){
        root = new Node();
        size = 0;
    }

    /**
     * 获得Trie中存储的单词数量
     * @return
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 向Trie中添加一个新的单词word
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    public void addRecu(String word){
        add(root,word);
    }

    private void add(Node node, String word){
        if(word == null || word.length() == 0 )
            return ;
        char c = word.charAt(0);
        if(node.next.get(c) == null)
            node.next.put(c,new Node());
        node = node.next.get(c);
        if(word.length() == 1){
            if(!node.isWord) {
                node.isWord = true;
                size++;
            }
        }
        else
            add(node,word.substring(1));
    }

    /**
     * 查询单词word是否在Trie中
     * @param word
     */
    public boolean contains(String word){
        Node cur = root;
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 查询是否在Trie中有单词以prefix为前缀
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){
        Node cur = root;
        for(int i = 0;i < prefix.length();i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) != null)
                cur = cur.next.get(c);
            else
                return false;
        }
        return true;
    }
}
