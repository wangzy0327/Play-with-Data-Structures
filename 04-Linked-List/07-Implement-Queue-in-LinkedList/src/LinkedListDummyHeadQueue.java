public class LinkedListDummyHeadQueue<E> implements Queue<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead,tail;
    private int size;

    public LinkedListDummyHeadQueue(){
        dummyHead = new Node(null,null);
        tail = dummyHead;
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        tail.next = new Node(e);
        tail = tail.next;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        Node retNode = dummyHead.next;
        dummyHead.next = retNode.next;
        retNode.next = null;
        if(dummyHead.next == null)
            tail = null;
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return dummyHead.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");
        Node cur = dummyHead.next;
        while(cur != null){
            res.append(cur+"->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }
}
