import java.util.Map;
import java.util.TreeMap;

/**
 * leetcode 211  添加与搜索单词 - 数据结构设计
 */
public class WordDictionary {

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

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());
            cur = cur.next.get(c);
        }
        if(!cur.isWord)
            cur.isWord = true;
    }

    public boolean search(String word) {
        return match(root,word,0);
    }

    private boolean match(Node cur,String word,int index){
        if(index == word.length())
            return cur.isWord;
        char c = word.charAt(index);
        if(c != '.'){
            if(cur.next.get(c) == null)
                return false;
            else
                return match(cur.next.get(c),word,index + 1);
        }else{
            for(char nextChar : cur.next.keySet())
                if(match(cur.next.get(nextChar),word,index + 1))
                    return true;
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
