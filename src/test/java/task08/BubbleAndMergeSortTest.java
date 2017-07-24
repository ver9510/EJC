package task08;

import org.junit.Test;

import static org.junit.Assert.*;

public class BubbleAndMergeSortTest {
    private int[] array = {43, 65, 76, 12, 34, 54, 32, 765, 987, 87};
    private int[] sortedArray = {12, 32, 34, 43, 54, 65, 76, 87, 765, 987};

    @Test
    public void testDoBubbleSort() {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        int[] arraySortedWithBubbleSort = sort.doMergeSort(array);
        assertArrayEquals(sortedArray, arraySortedWithBubbleSort);
    }

    @Test
    public void testDoMergeSort() {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        int[] arraySortedWithMergeSort = sort.doMergeSort(array);
        assertArrayEquals(sortedArray, arraySortedWithMergeSort);
    }

    @Test
    public void testBinarySearch() {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        assertEquals(5, sort.binarySearch(65, 0, sortedArray.length - 1, sortedArray));
        assertEquals(2, sort.binarySearch(34, 0, sortedArray.length - 1, sortedArray));
        assertEquals(8, sort.binarySearch(765, 0, sortedArray.length - 1, sortedArray));
    }
}
