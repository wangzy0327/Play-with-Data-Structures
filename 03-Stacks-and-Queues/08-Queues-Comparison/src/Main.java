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
        int opCount = 100000;

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue,opCount);
        System.out.println("ArrayQueue, time: "+time1+" s");

        Queue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue,opCount);
        System.out.println("LoopQueue, time: "+time2+" s");

//        ArrayQueue, time: 4.300757767 s
//        LoopQueue, time: 0.014331637 s

    }
}
