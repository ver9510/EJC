package task08;

import java.util.Arrays;
import java.util.Random;

public class BubbleAndMergeSort {
    private static int COUNT_OF_ELEMENTS = 20;

    /**
     * Main method. Firstly, array is filled with random numbers, then it sorts with bubble sort
     * and after the original array is sorted with merge sort. In the ned, results of sortings is printed.
     */
    public static void main(String[] args) {
        BubbleAndMergeSort main = new BubbleAndMergeSort();
        int[] arrayForSort = new int[COUNT_OF_ELEMENTS];
        Random rnd = new Random();
        for (int i = 0; i < COUNT_OF_ELEMENTS; i++) {
            arrayForSort[i] = rnd.nextInt(1000);
        }
        System.out.println("Before sort");
        main.printArray(arrayForSort);
        int[] arraySortedWithBubbleSort = main.doBubbleSort(arrayForSort);
        int[] arraySortedWithMergeSort = main.doMergeSort(arrayForSort);
        System.out.println("After Bubble sort");
        main.printArray(arraySortedWithBubbleSort);
        System.out.println("After Merge sort");
        main.printArray(arraySortedWithMergeSort);
        int keyElement = arrayForSort[rnd.nextInt(arrayForSort.length)];
        System.out.println("Search for " + keyElement);
        int foundElementIndex = main.binarySearch(keyElement, 0, arraySortedWithBubbleSort.length - 1, arraySortedWithBubbleSort);
        System.out.println("Index of " + keyElement + " is " + foundElementIndex);
    }

    /**
     * Sorts array with merge sort. Divides array in two until length of array will be 1, and merges it in the right order.
     *
     * @param arrayForSort - array, that we need to sort
     * @return sorted array
     */
    public int[] doMergeSort(int[] arrayForSort) {
        int middleOfArray = arrayForSort.length / 2;
        int[] leftArray = new int[middleOfArray];
        int[] rightArray = new int[arrayForSort.length - middleOfArray];
        int[] result;
        if (arrayForSort.length <= 1) {
            return arrayForSort;
        } else {
            for (int i = 0; i < arrayForSort.length; i++) {
                if (i < middleOfArray) {
                    leftArray[i] = arrayForSort[i];
                } else {
                    rightArray[i - middleOfArray] = arrayForSort[i];
                }
            }
            leftArray = doMergeSort(leftArray);
            rightArray = doMergeSort(rightArray);
            result = merge(leftArray, rightArray);
            return result;
        }
    }

    /**
     * Merges two parts of array
     *
     * @param leftArray  - first part of resulting array
     * @param rightArray - second part of array
     * @return sorted array, which contains elements from leftArray and rightArray.
     */
    private int[] merge(int[] leftArray, int[] rightArray) {
        int[] result = new int[leftArray.length + rightArray.length];
        int comparingLeftArrayElementIndex = 0;
        int comparingRightArrayElementIndex = 0;
        int indexForResultArray = 0;
        while (comparingLeftArrayElementIndex < leftArray.length && comparingRightArrayElementIndex < rightArray.length) {
            if (leftArray[comparingLeftArrayElementIndex] < rightArray[comparingRightArrayElementIndex]) {
                result[indexForResultArray] = leftArray[comparingLeftArrayElementIndex];
                comparingLeftArrayElementIndex++;
            } else {
                result[indexForResultArray] = rightArray[comparingRightArrayElementIndex];
                comparingRightArrayElementIndex++;
            }
            indexForResultArray++;
        }
        while (comparingLeftArrayElementIndex < leftArray.length) {
            result[indexForResultArray] = leftArray[comparingLeftArrayElementIndex];
            comparingLeftArrayElementIndex++;
            indexForResultArray++;
        }
        while (comparingRightArrayElementIndex < rightArray.length) {
            result[indexForResultArray] = rightArray[comparingRightArrayElementIndex];
            comparingRightArrayElementIndex++;
            indexForResultArray++;
        }
        return result;
    }

    /**
     * Sorts array with Bubble Sort
     *
     * @param arrayToSort - array, that we need to sort
     * @return sorted array
     */
    public int[] doBubbleSort(int[] arrayToSort) {
        int[] sortedArray = Arrays.copyOf(arrayToSort, arrayToSort.length);
        int lastIndex = sortedArray.length - 1;
        boolean exitFlag = false;
        for (int i = 0; i < lastIndex - 1; i++) {
            for (int j = 0; j < lastIndex - i; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                    exitFlag = true;
                }
            }
            if (!exitFlag) {
                break;
            }
        }
        return sortedArray;
    }

    /**
     * Prints array from argument
     *
     * @param arrayToPrint - array, that should be printed
     */
    private void printArray(int[] arrayToPrint) {
        for (int element : arrayToPrint) {
            System.out.println(element);
        }
    }

    /**
     * Search element in sorted array
     *
     * @param keyElement        - required number
     * @param startIndex        - index of the start of the array
     * @param endIndex          - index of the end of the array
     * @param arrayForSearching - array in which we search element
     * @return index of required element
     */
    public int binarySearch(int keyElement, int startIndex, int endIndex, int[] arrayForSearching) {
        int resultElementIndex = 0;
        int middleElementIndex = startIndex + (endIndex - startIndex) / 2 + 1;
        if (keyElement == arrayForSearching[middleElementIndex]) {
            resultElementIndex = middleElementIndex;
        } else {
            if (keyElement < arrayForSearching[middleElementIndex]) {
                resultElementIndex = binarySearch(keyElement, startIndex, middleElementIndex - 1, arrayForSearching);
            } else {
                resultElementIndex = binarySearch(keyElement, middleElementIndex + 1, endIndex, arrayForSearching);
            }
        }
        return resultElementIndex;
    }
}
