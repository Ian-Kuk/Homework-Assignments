import org.junit.Test;

import static org.junit.Assert.*;


public class minHeapTest {

    Integer[] numbers = {5,32,1,3,5,2,1,6};
    Integer[] orderedNumbers = {1,3,1,6,5,5,2,32};
    Integer[] sortedNumbers = {1,1,2,3,5,5,6,32};

    MinHeap<Integer> minHeapInt;

    public void init() {
        minHeapInt = new MinHeap<>();
    }

    /**  ------------------------------  Tests for Ints ---------------------------           **/
    @Test
    public void test_add_int() {
        init();
        // Adding elements to minHeap
        for (int i=0; i< numbers.length; i++)
            minHeapInt.add(numbers[i]);
        // Testing for the correct order of elements in the minHeapInt
        for (int i=0; i<numbers.length; i++)
            assertEquals(minHeapInt.getIndexVal(i), orderedNumbers[i]);

    }

    @Test
    public void test_poll_int(){
        init();
        // Check if the elements are added in correct order
        for (int i=0; i< numbers.length; i++)
            minHeapInt.add(numbers[i]);

        for (int i=0; i<numbers.length; i++)
            assertEquals(sortedNumbers[i], minHeapInt.poll());
    }


    @Test
    public void test_peek_int(){
        init();

        for (int i=0; i< numbers.length; i++)
            minHeapInt.add(numbers[i]);

        assertEquals(orderedNumbers[0], minHeapInt.peek());
    }

    @Test
    public void test_isEmpty_int(){
        init();

        for (int i=0; i< numbers.length; i++)
            minHeapInt.add(numbers[i]);

        assertEquals(false, minHeapInt.isEmpty());

    }

    @Test
    public void test_getSize_int(){
        init();

        assertEquals(0, minHeapInt.getSize());
        for (int i=0; i< numbers.length; i++)
            minHeapInt.add(numbers[i]);
        assertEquals(numbers.length, minHeapInt.getSize());

    }

}
