package task09;

import org.junit.Test;

import static org.junit.Assert.*;

public class RadixAndSelectionSortTest {
    private int[] array = {43, 65, 76, 12, 34, 54, 32, 765, 987, 87};
    private int[] sortedArray = {12, 32, 34, 43, 54, 65, 76, 87, 765, 987};

    @Test
    public void testDoRadixSort(){
        RadixAndSelectionSort sort=new RadixAndSelectionSort();
        int[] resultArray=sort.doRadixSort(array);
        assertArrayEquals(sortedArray,resultArray);
    }

    @Test
    public void testDoSelectionSort(){
        RadixAndSelectionSort sort=new RadixAndSelectionSort();
        int[] resultArray=sort.doSelectionSort(array);
        assertArrayEquals(sortedArray,resultArray);
    }

}