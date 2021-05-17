import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private class Node{
        boolean isWord;
        Map<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie(){
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

    private void add(Node node,String word){
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

    /**
     * 删除word，返回是否删除成功，递归算法
     * @param word
     * @return
     */
    public boolean remove(String word){
        if(word.equals(""))
            return false;
        return remove(root,word,0);
    }

    /**
     * 在以Node为根的Trie中删除单词word[index...end),返回是否删除成功, 递归算法
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean remove(Node node,String word,int index){
        if(index == word.length()){
            if(node.isWord){
                node.isWord = false;
                size--;
                return true;
            }
            return false;
        }

        char c = word.charAt(index);
        if(node.next.get(c) == null)
            return false;

        Node nextNode = node.next.get(c);
        boolean ret = remove(nextNode,word,index + 1);
        if(!nextNode.isWord && nextNode.next.size() == 0)
            node.next.remove(c);
        return ret;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.add("apot");
        System.out.println(trie.contains("apot"));

        trie.remove("apot");
        System.out.println(trie.contains("apot"));
    }

}
