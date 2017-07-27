package task09;

import java.util.Arrays;
import java.util.Random;

public class RadixAndSelectionSort {
    private static final int COUNT_OF_ELEMENTS = 10;

    /**
     * Main method. Generates random numbers, calls methods for radix sort and selection sort and prints results.
     */
    public static void main(String[] args) {
        Random rnd = new Random();
        int[] arrayForSort = new int[COUNT_OF_ELEMENTS];
        for (int i = 0; i < COUNT_OF_ELEMENTS; i++) {
            arrayForSort[i] = rnd.nextInt(1000);
            System.out.println(arrayForSort[i]);
        }
        RadixAndSelectionSort sort = new RadixAndSelectionSort();
        int[] resultArray1 = sort.doRadixSort(arrayForSort);
        System.out.println("After Radix sort");
        for (int element : resultArray1) {
            System.out.println(element);
        }
        int[] resultArray2 = sort.doSelectionSort(arrayForSort);
        System.out.println("After Selection sort");
        for (int element : resultArray2) {
            System.out.println(element);
        }
    }

    /**
     * Sorts input array with Radix sort.
     *
     * @param inputArray - array for sorting
     * @return sorted array
     */
    public int[] doRadixSort(int[] inputArray) {
        int[] arrayForSort = Arrays.copyOf(inputArray, inputArray.length);
        int maxElement = getMaxElement(arrayForSort);
        int exp = 1;
        while (maxElement / exp > 0) {
            int[] buckets = new int[10];
            int[] resultArray = new int[arrayForSort.length];
            for (int i = 0; i < arrayForSort.length; i++) {
                int digit = (arrayForSort[i] / exp) % 10;
                buckets[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                buckets[i] += buckets[i - 1];
            }
            for (int i = arrayForSort.length - 1; i >= 0; i--) {
                int digit = (arrayForSort[i] / exp) % 10;
                buckets[digit]--;
                resultArray[buckets[digit]] = arrayForSort[i];
            }
            arrayForSort = resultArray;
            exp *= 10;
        }
        return arrayForSort;
    }

    /**
     * Sorts input array with Selection sort.
     *
     * @param inputArray - array for sorting
     * @return sorted array
     */
    public int[] doSelectionSort(int[] inputArray) {
        int[] arrayForSort = Arrays.copyOf(inputArray, inputArray.length);
        for (int i = 0; i < arrayForSort.length; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arrayForSort.length; j++) {
                if (arrayForSort[j] < arrayForSort[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            if (minElementIndex != i) {
                int temp = arrayForSort[minElementIndex];
                arrayForSort[minElementIndex] = arrayForSort[i];
                arrayForSort[i] = temp;
            }
        }
        return arrayForSort;
    }

    /**
     * Gets maximum element from input array.
     *
     * @param array - array, where we need to find max element
     * @return max element of array
     */
    private int getMaxElement(int[] array) {
        int maxElem = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxElem) {
                maxElem = array[i];
            }
        }
        return maxElem;
    }
}
