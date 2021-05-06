import java.util.Random;

public class Main {

    private static double testStack(Stack<Integer> stack,int opCount){
        long startTime = System.nanoTime();

        // ...
        Random random = new Random();
        for(int i = 0;i < opCount;i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for(int i = 0;i < opCount;i++)
            stack.pop();

        long endTime = System.nanoTime();
        //转换为秒
        return (endTime - startTime)/1000000000.0;
    }

    public static void main(String[] args) {
//        LinkeListStack<Integer> stack = new LinkeListStack<>();
//        for(int i = 0;i < 5;i++){
//            stack.push(i);
//            System.out.println(stack);
//        }
//
//        stack.pop();
//        System.out.println(stack);

//        Stack : top 0->NULL
//        Stack : top 1->0->NULL
//        Stack : top 2->1->0->NULL
//        Stack : top 3->2->1->0->NULL
//        Stack : top 4->3->2->1->0->NULL
//        Stack : top 3->2->1->0->NULL

        int opCount = 100000;

        Stack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack,opCount);
        System.out.println("ArrayStack, time: "+time1+" s");

        Stack<Integer> linkedListStack = new LinkeListStack<>();
        double time2 = testStack(arrayStack,opCount);
        System.out.println("LinkedListStack, time: "+time2+" s");

//        ArrayStack, time: 0.017826384 s
//        LinkedListStack, time: 0.010944404 s

        //ArrayStack 空间不够了 到一定程度需要重新 拷贝复制
        //其实这个时间比较很复杂，因为LinkedListStack中包含更多的new操作



    }
}
