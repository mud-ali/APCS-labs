package anagrams;

import java.util.ArrayList;

public class ListUtils {
    public static int indexOf(ArrayList<String> arr, String item) {
        return binarySearch(item, 0, arr.size(), arr);
    }

    public static boolean contains(ArrayList<String> arr, String item) {
        return indexOf(arr, item) != -1;
    }

    private static int binarySearch(String word, int s, int e, ArrayList<String> arr) {
        int mid = (s + e) / 2;
        if (s > e || s > arr.size())
            return -1;
        String midWord = arr.get(mid);
        if (midWord.equals(word))
            return mid;
        if (word.compareTo(midWord) < 0)
            return binarySearch(word, s, mid - 1, arr);
        return binarySearch(word, mid + 1, e, arr);
    }
}
