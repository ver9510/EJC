package task08;

import org.junit.Test;
import org.junit.Assert;

public class BubbleAndMergeSortTest {
    private int[] array = {43, 65, 76, 12, 34, 54, 32, 765, 987, 87};
    private int[] sortedArray = {12, 32, 34, 43, 54, 65, 76, 87, 765, 987};

    @Test
    public void testDoBubbleSort() {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        int[] arraySortedWithBubbleSort = sort.doMergeSort(array);
        Assert.assertArrayEquals(sortedArray, arraySortedWithBubbleSort);
    }

    @Test
    public void testDoMergeSort() {
        BubbleAndMergeSort sort = new BubbleAndMergeSort();
        int[] arraySortedWithMergeSort = sort.doMergeSort(array);
        Assert.assertArrayEquals(sortedArray, arraySortedWithMergeSort);
    }
}
