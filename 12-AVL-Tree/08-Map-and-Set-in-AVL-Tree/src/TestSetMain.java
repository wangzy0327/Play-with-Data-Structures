import java.util.ArrayList;

public class TestSetMain {

    private static double testSet(Set<String> set,String filename){
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e9d;
    }

    public static void main(String[] args) {
        String filename = "pride-and-prejudice.txt";

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double time1 = testSet(linkedListSet,filename);
        System.out.println("Linked List Set : "+time1+" s ");

        System.out.println();

        BSTSet<String> bstSet = new BSTSet<>();
        double time2 = testSet(bstSet,filename);
        System.out.println("BST Set : "+time2+" s ");

        System.out.println();

        AVLSet<String> avlSet = new AVLSet<>();
        double time3 = testSet(avlSet,filename);
        System.out.println("AVL Set : "+time3+" s ");


//        pride-and-prejudice.txt
//        Total words: 125901
//        Total different words: 6530
//        Linked List Set : 2.744987957 s
//
//        pride-and-prejudice.txt
//        Total words: 125901
//        Total different words: 6530
//        BST Set : 0.081180348 s
//
//        pride-and-prejudice.txt
//        Total words: 125901
//        Total different words: 6530
//        AVL Set : 0.070529892 s

    }
}
