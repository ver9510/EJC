package task07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sort {
    /**
     * Main method - calls methods sortNumbersFromKeyboardWithQuickSort and sortRandomNumbersWithInsertionsSort
     */
    public static void main(String[] args) {
        Sort main = new Sort();
        main.sortNumbersFromKeyboardWithQuickSort();
        main.sortRandomNumbersWithInsertionsSort(1000, 100);
    }

    /**
     * Generates random numbers, puts it in the arraylist, prints it, sorts it with Quick sort
     * and prints result - sorted list.
     */
    private void sortNumbersFromKeyboardWithQuickSort() {
        ArrayList<Integer> listForSorting = readNumbers();
        printList(listForSorting);
        doQuickSort(listForSorting, 0, listForSorting.size() - 1);
        System.out.println("----------");
        printList(listForSorting);
    }

    /**
     * Generates random numbers, puts it in the array, prints this array, sorts it with Insertion sort
     * and prints result - sorted array
     *
     * @param bound - maximum value for random generator
     * @param count - count of generated elements
     */
    private void sortRandomNumbersWithInsertionsSort(int bound, int count) {
        Integer[] arrayForSort = getRandomNumbers(bound, count);
        printArray(arrayForSort);
        doInsertionSort(arrayForSort);
        System.out.println("----------");
        printArray(arrayForSort);
    }

    /**
     * Radomly generates numbers and puts it in arraylist
     *
     * @param bound - maximum value for random generator
     * @param count - count of generated elements
     * @return arrayForSort - array of random integers
     */
    private Integer[] getRandomNumbers(int bound, int count) {
        Integer[] arrayForSort = new Integer[count];
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            arrayForSort[i] = rnd.nextInt(bound);
        }
        return arrayForSort;
    }

    /**
     * Takes numbers from standard input(keyboard) and puts it in ArrayList
     *
     * @return listForSort - parametrized ArrayList with integers
     */
    private ArrayList<Integer> readNumbers() {
        ArrayList<Integer> listForSort = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            listForSort.add(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listForSort;
    }

    /**
     * Sorts list of Integers with QuickSort
     *
     * @param list       - parametrized list with integers for sorting
     * @param startIndex - index of first element of list to sort
     * @param endIndex   - index of last element of list to sort
     */
    public void doQuickSort(List<Integer> list, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int keyElementIndex = (startIndex + endIndex) / 2;
            int keyElement = list.get(keyElementIndex);
            int i = startIndex;
            int j = endIndex;
            while (i < j) {
                while (list.get(i) < keyElement) {
                    i++;
                }
                while (list.get(j) > keyElement) {
                    j--;
                }
                if (i <= j) {
                    Collections.swap(list, i, j);
                    i++;
                    j--;
                }
            }
            if (startIndex < j) {
                doQuickSort(list, startIndex, j);
            }
            if (endIndex > i) {
                doQuickSort(list, i, endIndex);
            }
        }
    }

    /**
     * Sorts array with Insertion sort
     *
     * @param arrayForSort - array with integers for sorting
     */
    public void doInsertionSort(Integer[] arrayForSort) {
        int keyElement;
        for (int i = 1; i < arrayForSort.length; i++) {
            keyElement = arrayForSort[i];
            int j = i - 1;
            while (j >= 0 && arrayForSort[j] > keyElement) {
                arrayForSort[j + 1] = arrayForSort[j];
                j--;
            }
            arrayForSort[j + 1] = keyElement;
        }
    }

    /**
     * Prints list with elements
     *
     * @param list - parametrized List with integers
     */
    private void printList(List<Integer> list) {
        for (int elem : list) {
            System.out.println(elem);
        }
    }

    /**
     * Prints array
     *
     * @param arrayToPrint - array of integers for printing
     */
    private void printArray(Integer[] arrayToPrint) {
        for (Integer anArrayToPrint : arrayToPrint) {
            System.out.println(anArrayToPrint);
        }
    }
}