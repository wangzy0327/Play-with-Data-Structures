import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static double testMap(Map<String,Integer> map,String filename){
        long startTime = System.nanoTime();
        System.out.println(filename);

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            //有序 or 无序
//            Collections.sort(words);

            startTime = System.nanoTime();
            System.out.println("Total words : "+words.size());
            for(String word:words){
                if(!map.contains(word))
                    map.add(word,1);
                else
                    map.set(word,map.get(word) + 1);
            }
            for(String word: words)
                map.contains(word);

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        long endTime = System.nanoTime();
        return (endTime - startTime)/1e9d;

    }

    public static void main(String[] args) {
//        System.out.println("Pride and Prejudice");
        String filename = "pride-and-prejudice.txt";


        // Test BST
        BSTMap<String,Integer> bstMap = new BSTMap<>();
        double time2 = testMap(bstMap,filename);
        System.out.println("BST Map : "+time2+" s ");

        System.out.println();


        // Test AVL
        AVLTree<String,Integer> avlMap = new AVLTree<>();
        double time3 = testMap(avlMap,filename);
        System.out.println("AVL Map : "+time3+" s ");

        System.out.println();

        // Test AVL Advance
        AVLTreeAdv<String,Integer> avlMapAdv = new AVLTreeAdv<>();
        double time4 = testMap(avlMapAdv,filename);
        System.out.println("AVL Map Advance : "+time4+" s ");

        // no-order
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        BST Map : 0.109991376 s
//
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        AVL Map : 0.117440256 s
//
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        AVL Map Advance : 0.108915624 s

        //orderly
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        BST Map : 18.341632386 s
//
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        AVL Map : 0.070439422 s
//
//        pride-and-prejudice.txt
//        Total words : 125901
//        Total different words: 6530
//        Frequency of PRIDE: 53
//        Frequency of PREJUDICE: 11
//        AVL Map Advance : 0.083662913 s

        System.out.println();
    }
}
