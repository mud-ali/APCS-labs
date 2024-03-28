package anagrams;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnagramFinder {

    private static void getAnagrams(String remaining, String startingbit, ArrayList<String> used, LookupList lookup) {
        if (remaining.length()==0) {
            if (lookup.contains(startingbit) && !used.contains(startingbit))
                used.add(startingbit);
            return;
        }               
        
        for (int wordChoice=0;wordChoice<remaining.length();wordChoice++) {
            String leftOvers = remaining.substring(0, wordChoice) + remaining.substring(wordChoice+1);
            String newWord = startingbit+remaining.charAt(wordChoice);
            getAnagrams(leftOvers, newWord, used, lookup);
        }
    }

    public static List<String> getAnagrams(String word, LookupList dict) {
        ArrayList<String> anagrams = new ArrayList<>();
        getAnagrams(word, "", anagrams, dict);
        return anagrams;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("Which word list?  (large, medium, small, tiny)");
        String listSize = input.nextLine();
        String filename = "data/scrabbleWords/words_" + listSize + ".txt";

        System.out.printf("Reading in word list from file %s...\n", filename);
        double start = System.nanoTime();
        LookupList dict = new LookupList(filename);
        System.out.print("Successfully read in word list from file!\n");
        System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime() - start) / 1000000000);

        System.out.print("\n\nEnter word/letters to find anagrams (enter blank to stop) >>>  ");
        String word = input.nextLine().toUpperCase();
        while (!word.equals("")) {
            start = System.nanoTime();
            System.out.printf("Searching for anagrams of \"%s\"...\n", word);
            List<String> anagrams = getAnagrams(word, dict);
            System.out.printf("Found %d anagrams!\n", anagrams.size());
            System.out.println(anagrams);
            System.out.printf("Time: %.4f seconds.\n\n", (System.nanoTime() - start) / 1000000000);

            System.out.print("Enter another word to find anagrams >>> ");
            word = input.nextLine().toUpperCase();
            ;
        }
        System.out.println("All done!");
        input.close();
    }

}
