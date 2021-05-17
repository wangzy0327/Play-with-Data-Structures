import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set,String filename){
        long startTime = System.nanoTime();
        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.contains(word);
        }
        long endTime = System.nanoTime();

        double time = (endTime - startTime)/1e9d;
        System.out.println("Total different words: " + set.getSize());
        return time;
    }

    public static void main(String[] args) {
        Set<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet,"pride-and-prejudice.txt");
        System.out.println("BSTSet: " + time1 + " s");
        System.out.println();
        Set<String> trieSet = new TrieSet<>();
        double time2 = testSet(trieSet,"pride-and-prejudice.txt");
        System.out.println("TrieSet: " + time2 + " s");


//        pride-and-prejudice.txt
//        Total different words: 6530
//        BSTSet: 0.1475566 s
//
//        pride-and-prejudice.txt
//        Total different words: 6530
//        TrieSet: 0.0926995 s

    }
}
