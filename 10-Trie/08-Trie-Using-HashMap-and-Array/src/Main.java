import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static double testSet(Set<String> set,String[] filenames){
        long startTime = System.nanoTime();
        System.out.println(Arrays.toString(filenames));
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filenames[0],words) &&
                FileOperation.readFile(filenames[1],words)){
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
        String [] filenames = {"pride-and-prejudice.txt","a-tale-of-two-cities.txt"};
        double time = testSet(bstSet,filenames);
        System.out.println("BSTSet: " + time + " s");
        System.out.println();
        Set<String> trieSet = new TrieSet<>();
        time = testSet(trieSet,filenames);
        System.out.println("TreeMap TrieSet: " + time + " s");
        System.out.println();
        Set<String> trieSet2 = new TrieSet<>();
        time = testSet(trieSet,filenames);
        System.out.println("HashMap TrieSet2: " + time + " s");
        System.out.println();
        Set<String> trieSet3 = new TrieSet<>();
        time = testSet(trieSet,filenames);
        System.out.println("Array(Map) TrieSet3: " + time + " s");

        /*
                [pride-and-prejudice.txt, a-tale-of-two-cities.txt]
        Total different words: 11882
        BSTSet: 0.2226049 s

                [pride-and-prejudice.txt, a-tale-of-two-cities.txt]
        Total different words: 11882
        TreeMap TrieSet: 0.1399792 s

                [pride-and-prejudice.txt, a-tale-of-two-cities.txt]
        Total different words: 11882
        HashMap TrieSet2: 0.1034066 s

                [pride-and-prejudice.txt, a-tale-of-two-cities.txt]
        Total different words: 11882
        Array(Map) TrieSet3: 0.113744 s


         */

    }
}
