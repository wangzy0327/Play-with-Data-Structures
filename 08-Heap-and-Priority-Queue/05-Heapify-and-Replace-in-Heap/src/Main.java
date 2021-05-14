import java.util.Random;

public class Main {

    private static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else{
            maxHeap = new MaxHeap<>();
            for(int num : testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0;i < testData.length;i++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1;i < testData.length;i++)
            if(arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e9d;
    }

    public static void main(String[] args) {
        int n = 10000000;

        Integer[] testData = new Integer[n];
        Random random = new Random();
        for(int i = 0;i < n;i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        double time1 = testHeap(testData,false);
        System.out.println("Without heapify : "+time1 +" s ");

        double time2 = testHeap(testData,true);
        System.out.println("With heapify : "+time2 +" s ");

        //数据规模为10,000,000
//        Test MaxHeap completed.
//                Without heapify : 14.0322853 s
//        Test MaxHeap completed.
//                With heapify : 10.7878608 s

    }
}
