package task09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RadixAndSelectionSort {
    private static final int COUNT_OF_ELEMENTS = 10;

    /**
     * Main method. Generates random numbers, calls methods for radix sort and selection sort and prints results.
     */
    public static void main(String[] args) {
        RadixAndSelectionSort sort = new RadixAndSelectionSort();
        int[] arrayForSort = sort.fillArrayWithRandomNumbers();
        int[] resultArray1 = sort.doRadixSort(arrayForSort);
        System.out.println("After Radix sort");
        sort.printArray(resultArray1);
        int[] resultArray2 = sort.doSelectionSort(arrayForSort);
        System.out.println("After Selection sort");
        sort.printArray(resultArray2);
        int[] resultArray3 = sort.radixSortMSD(arrayForSort);
        System.out.println("After MSD variant of Radix sort  sort");
        sort.printArray(resultArray3);
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

    /**
     * Sort input array with Radix sort using MSD variant
     *
     * @param inputArray - array for sorting
     * @return sorted array
     */
    private int[] radixSortMSD(int[] inputArray) {
        int[] arrayForSort = Arrays.copyOf(inputArray, inputArray.length);
        int maxElement = getMaxElement(arrayForSort);
        int power = (int) Math.log10(maxElement);
        int exp = (int) Math.pow(10, power);
        int[] resultArray = new int[arrayForSort.length];
        List<Integer> listForSort = new ArrayList<>();
        for (int i = 0; i < arrayForSort.length; i++) {
            listForSort.add(arrayForSort[i]);
        }
        List<Integer> resultList = splitAndSort(listForSort, exp);
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    /**
     * Splts array in different buckets by digits, calls this method recursively on each bucket(sorts) and
     * compounds sorted results in one array
     *
     * @param listForSort - list for sorting
     * @param exp         -    by which digit we need to sort
     * @return sorted list
     */
    private List<Integer> splitAndSort(List<Integer> listForSort, int exp) {
        if (listForSort.size() > 1) {
            ArrayList<ArrayList> bucketsList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                bucketsList.add(new ArrayList<Integer>());
            }
            for (int i = 0; i < listForSort.size(); i++) {
                int digit = (listForSort.get(i) / exp % 10);
                bucketsList.get(digit).add(listForSort.get(i));
            }
            List resultList = new ArrayList();
            for (int i = 0; i < 10; i++) {
                if (bucketsList.get(i).size() > 0) {
                    resultList.addAll(splitAndSort(bucketsList.get(i), exp / 10));
                }
            }
            return resultList;
        } else {
            return listForSort;
        }
    }

    /**
     * Generates random numbers and fills array
     *
     * @return array with random numbers
     */
    private int[] fillArrayWithRandomNumbers() {
        Random rnd = new Random();
        int[] arrayForSort = new int[COUNT_OF_ELEMENTS];
        for (int i = 0; i < COUNT_OF_ELEMENTS; i++) {
            arrayForSort[i] = rnd.nextInt(1000);
            System.out.println(arrayForSort[i]);
        }
        return arrayForSort;
    }

    /**
     * Prints array
     *
     * @param arrayToPrint - array of integers for printing
     */
    private void printArray(int[] arrayToPrint) {
        for (int element : arrayToPrint) {
            System.out.println(element);
        }
    }
}
