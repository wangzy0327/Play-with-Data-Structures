public class TrieSet<E extends String> implements Set<E> {

    private Trie trie;

    public TrieSet() {
        this.trie = new Trie();
    }

    @Override
    public void add(E e) {
//        trie.addRecu(e);
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
