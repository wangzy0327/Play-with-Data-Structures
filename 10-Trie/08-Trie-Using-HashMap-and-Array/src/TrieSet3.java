public class TrieSet3<E extends String> implements Set<E>  {
    private Trie3 trie;

    public TrieSet3() {
        this.trie = new Trie3();
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
