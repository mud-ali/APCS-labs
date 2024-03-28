package anagrams;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LookupList {
    private ArrayList<String> wordlist;

    public LookupList() {
        wordlist = new ArrayList<String>();
    }

    public LookupList(String fileName) throws FileNotFoundException {
        wordlist = new ArrayList<String>();
        Scanner file = new Scanner(new FileReader(fileName));

        while (file.hasNextLine()) {
            String word = file.nextLine();
            // skip blank lines or starting with #, treating them as comments
            if (word.length() == 0 || word.charAt(0) == '#')
                continue;

            wordlist.add(word);
        }
        file.close();

        mergeSort();
    }

    public int size() {
        return wordlist.size();
    }

    public String get(int index) {
        return wordlist.get(index);
    }

    private int binarySearch(String word, int s, int e) {
        int mid = (s+e)/2;
        if (s > e || s > wordlist.size()) return -1;
        String midWord = wordlist.get(mid);
        if (midWord.equals(word)) return mid;
        if (word.compareTo(midWord) < 0) 
            return binarySearch(word, s, mid-1);
        return binarySearch(word, mid+1, e);
    }

    public int indexOf(String word) {
        return binarySearch(word, 0, wordlist.size());
    }

    public boolean contains(String word) {
        return indexOf(word)!=-1;
    }

    public List<String> toList() {
        return wordlist;
    }

    public void print() {
        int maxdigits = String.valueOf(wordlist.size()).length();
        
        String format = "%" + maxdigits + "d: %s\n";

        System.out.printf("Wordlist (%d total):\n", wordlist.size());
        for (int i = 0; i < wordlist.size(); i++) {
            System.out.printf(format, i, wordlist.get(i));
        }
    }

    /**
     * Insert a word into the already sorted list
     * @param word - the word to insert
     */
    public void insert(String word) {
        for (int i = wordlist.size(); i > 0; i--) {
            String w = wordlist.get(i - 1);
            if (word.compareTo(w) >= 0) {
                wordlist.add(i, word);
                return;
            }
        }
        wordlist.add(0, word); // if < all items in list, add to front of list
    }

    private void mergeSort() {
        wordlist = mergeSort(wordlist);
    }

    private ArrayList<String> mergeSort(ArrayList<String> list) {
        if (list.size()==1) return list;
        int half = list.size()/2;

        ArrayList<String> listA = new ArrayList<>(list.subList(0, half));
        ArrayList<String> listB = new ArrayList<>(list.subList(half, list.size()));
        return mergeLists(mergeSort(listA), mergeSort(listB));
    }

    private ArrayList<String> mergeLists(ArrayList<String> listA, ArrayList<String> listB) {
        ArrayList<String> combined = new ArrayList<>(listA.size() + listB.size());
        int a = 0, b = 0;

        while (a < listA.size() && b < listB.size()) {
            if (listA.get(a).compareTo(listB.get(b)) <= 0) {
                combined.add(listA.get(a++));
            } else {
                combined.add(listB.get(b++));
            }
        }

        while (a < listA.size()) combined.add(listA.get(a++));
        while (b < listB.size()) combined.add(listB.get(b++));

        return combined;
    }
}
