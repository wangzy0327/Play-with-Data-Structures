public class Trie3 {
    private class Node{
        boolean isWord;
        Node[] next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new Node[26];
        }

        public Node(){
            this(false);
        }
    }
    private Node root;
    private int size;

    public Trie3(){
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
            if(cur.next[c-'a'] == null)
                cur.next[c-'a'] = new Node();
            cur = cur.next[c-'a'];
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
        if(node.next[c-'a'] == null)
            node.next[c-'a'] = new Node();
        node = node.next[c-'a'];
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
            if(cur.next[c-'a'] == null)
                return false;
            cur = cur.next[c-'a'];
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
            if(cur.next[c-'a'] != null)
                cur = cur.next[c-'a'];
            else
                return false;
        }
        return true;
    }
}
