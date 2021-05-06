import java.util.Random;

public class Main {

    /**
     * 测试使用queue运行opCount个enqueue和dequeue操作所需要的时间，单位：秒
     * @param q
     * @param opCount
     * @return
     */
    private static double testQueue(Queue<Integer> q,int opCount){
        long startTime = System.nanoTime();

        // ...
        Random random = new Random();
        for(int i = 0;i < opCount;i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0;i < opCount;i++)
            q.dequeue();

        long endTime = System.nanoTime();
        //转换为秒
        return (endTime - startTime)/1000000000.0;
    }

    public static void main(String[] args) {
//        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        LinkedListDummyHeadQueue<Integer> queue = new LinkedListDummyHeadQueue<>();
        for(int i = 0;i < 10;i++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }


//        Queue: front 0->NULL tail
//        Queue: front 0->1->NULL tail
//        Queue: front 0->1->2->NULL tail
//        Queue: front 1->2->NULL tail
//        Queue: front 1->2->3->NULL tail
//        Queue: front 1->2->3->4->NULL tail
//        Queue: front 1->2->3->4->5->NULL tail
//        Queue: front 2->3->4->5->NULL tail
//        Queue: front 2->3->4->5->6->NULL tail
//        Queue: front 2->3->4->5->6->7->NULL tail
//        Queue: front 2->3->4->5->6->7->8->NULL tail
//        Queue: front 3->4->5->6->7->8->NULL tail
//        Queue: front 3->4->5->6->7->8->9->NULL tail


//        int opCount = 100000;
//
//        Queue<Integer> arrayQueue = new ArrayQueue<>();
//        double time1 = testQueue(arrayQueue,opCount);
//        System.out.println("ArrayQueue, time: "+time1+" s");
//
//        Queue<Integer> loopQueue = new LoopQueue<>();
//        double time2 = testQueue(loopQueue,opCount);
//        System.out.println("LoopQueue, time: "+time2+" s");
//
//        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
//        double time3 = testQueue(linkedListQueue,opCount);
//        System.out.println("LinkedListQueue, time: "+time3+" s");

//        ArrayQueue, time: 4.122922763 s
//        LoopQueue, time: 0.016529001 s
//        LinkedListQueue, time: 0.013985592 s

    }
}
