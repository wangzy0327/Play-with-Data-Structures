/**
 * 循环队列
 * LoopQueue<E>
 *     void enqueue(E)                O(1)均摊
 *     E  dequeue()                   O(1)均摊
 *     E  getFront()                  O(1)
 *     int getSize()                  O(1)
 *     boolean isEmpty()              O(1)
 *
 * @param <E>
 */
public class LoopQueue <E> implements Queue<E>{

    private E[] data;
    //规定 front == tail 队列为空, (tail+1)%c == front 队列满
    private int front,tail;
    private int size;

    /**
     * 因为会浪费一个空间，所以构造时需要比用户期望声明的空间多一个
     * @param capacity
     */
    public LoopQueue(int capacity){
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1)%data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E res = data[front];
        data[front] = null;
        front = (front + 1)%data.length;
        size--;
        if(size == getCapacity()/4 && getCapacity()/2 != 0 )
            resize(getCapacity()/2);
        return res;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is Empty.");;
        return data[front];
    }

    private void resize(int newCapacity){
        //要浪费一个空间
        E[] newData = (E[])new Object[newCapacity + 1];
        for(int j = 0;j < size;j++)
            newData[j] = data[(j+front)%data.length];
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for(int i = front;i != tail;i = (i+1)%data.length){
            res.append(data[i]);
            if((i+1)%data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

}
