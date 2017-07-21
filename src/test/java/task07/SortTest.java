package task07;

import org.junit.Test;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Arrays;

public class SortTest {
    private Integer[] array = {43, 65, 76, 12, 34, 54, 32, 765, 987, 87};
    private Integer[] sortedArray = {12, 32, 34, 43, 54, 65, 76, 87, 765, 987};

    @Test
    public void testDoQuickSort() {
        ArrayList<Integer> listForSort = new ArrayList<>();
        listForSort.addAll(Arrays.asList(array));
        ArrayList<Integer> sortedList = new ArrayList<>();
        sortedList.addAll(Arrays.asList(sortedArray));
        Sort sort = new Sort();
        sort.doQuickSort(listForSort, 0, listForSort.size() - 1);
        Assert.assertEquals(sortedList, listForSort);
    }

    @Test
    public void testDoInsertionSort() {
        Sort sort = new Sort();
        sort.doInsertionSort(array);
        Assert.assertEquals(Arrays.asList(sortedArray), Arrays.asList(array));
    }
}
