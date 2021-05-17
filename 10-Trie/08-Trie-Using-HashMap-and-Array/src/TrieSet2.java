public class TrieSet2<E extends String> implements Set<E>  {

    private Trie2 trie;

    public TrieSet2() {
        this.trie = new Trie2();
    }

    @Override
    public void add(E e) {
        trie.add(e);
    }

    @Override
    public void remove(E e) {
        //TODO
    }

    @Override
    public boolean contains(E e) {
        return trie.contains(e);
    }

    @Override
    public int getSize() {
        return trie.getSize();
    }

    @Override
    public boolean isEmpty() {
        return trie.getSize() == 0;
    }
}
