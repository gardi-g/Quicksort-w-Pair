import java.util.Random;
public class Partition {
    static class Pair {
        int left, right;
        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    static void swap(int array[],int i, int j)  {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    static void swap2(int array[],int i, int j, int m)  {
        int temp = array[i];
        array[i] = array[j];
        array[j] = array[m];
        array[m] = temp;
    }
    static Pair partition(int[] array, int p, int q) {
        int i = p;
        Pair pair = new Pair(p,p);

        while (i < q - 1) {
            i++;
            if (array[i] == array[pair.left] && i > pair.right) {
                swap(array, i, pair.right+1);
                pair.right++;
            } else if (array[i] < array[pair.left]) {
                swap2(array,i,pair.right+1,pair.left);
                pair.left++;
                pair.right++;
            }
        }
        return pair;
    }
    static void quicksort(int[] array, int p, int q) {
        if (p < q) {
            Pair pair = partition(array, p, q);
            quicksort(array, p, pair.left);
            quicksort(array, pair.right+1, q);
        }
    }
    static int sortOne(int[] array, int k) {
        return sortOne(array, k, 0, array.length);
    }
    static int sortOne(int[] array, int k, int p, int q) {
        if (p < q) {
            Pair pair = partition(array, p, q);
            if (k < pair.left) {
                return sortOne(array, k, p, pair.left);
            }
            if (k > pair.right) {
                return sortOne(array, k, pair.right + 1, q);
            }
        }
        return array[k];
    }
    static Random prng = new Random(42);
    public static void main(String[] args) {

        int test = 0;
        for (int size : new int[] {10, 100, 1000, 10000, 100_000, 1_000_000}) {
            for (int duplicates : new int[] {1, 2, 5, 10, 100}) {
                ++test;
                if (!testSort(size, duplicates)) {
                    System.err.printf("Failed sorting test %d, with array size of %d and number of duplicates %d\n", test, size, duplicates);
                    return;
                }
                ++test;
                if (!testSortOne(size, duplicates)) {
                    System.err.printf("Failed sort one test %d, with array size of %d and number of duplicates %d\n", test, size, duplicates);
                    return;
                }
            }
        }
        System.err.println("Passed all tests");
    }
    public static boolean testSort(int size, int duplicates) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i / duplicates;
        }
        for (int i = 0; i < size; i++) {
            int ndx = prng.nextInt(size);
            int tmp = array[i];
            array[i] = array[ndx];
            array[ndx] = tmp;
        }
        quicksort(array, 0, array.length);
        for (int i = 0; i < size; i++) {
            if (array[i] != i / duplicates) return false;
        }
        return true;
    }
    public static boolean testSortOne(int size, int duplicates) {
        for (int t = 0; t < 10; t++) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = i / duplicates;
            }
            for (int i = 0; i < size; i++) {
                int ndx = prng.nextInt(size);
                int tmp = array[i];
                array[i] = array[ndx];
                array[ndx] = tmp;
            }
            int ndx = prng.nextInt(size);
            int value = sortOne(array, ndx);
            if (value != ndx / duplicates) return false;
        }
        return true;
    }
}