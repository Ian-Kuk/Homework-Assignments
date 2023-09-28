import java.util.Arrays;

/** NOTE: YOU ONLY NEED TO MODIFY FUNCTIONS WHICH HAVE GREEN COMMENTS **/


public class MinHeap<E extends Comparable<E>> {
    private int capacity = 5;
    private int size = 0;
    private int Index;

    E[] items = (E[])new Comparable[capacity];


    // You can use this function to check how your heap looks at any given point.
    public void printHeap(){
        for (int i=0; i<items.length; i++)
            System.out.println(items[i]);
    }
    // Returns the value/object at the provided index
    public E getIndexVal(int index){
        return items[index];
    }

    /**      Returns the Index of left child       **/
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    /**      Returns the Index of left child       **/
    private int getRightChildIndex(int parentIndex){
        // Modify this function
        return 2 * parentIndex +2;
    }

    /**      Returns the Index of parent node       **/
    private int getParentIndex(int childIndex){
        // Modify this function
        return (childIndex - 1) / 2;
    }

    // Returns true if the left child of a node exists
    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index)<size;
    }

    // Returns true if the right child of a node exists
    private boolean hasRightChild(int index) {
        return getRightChildIndex(index)<size;
    }

    // Returns true if the parent of a node exists
    private boolean hasParent(int Index) {
        return getParentIndex(Index)>=0;
    }

    // Given an index of a node, get the element of the left child of that node.
    private E leftChild(int index) { return items[getLeftChildIndex(index)]; }
    // Given an index of a node, get the element of the right child of that node.
    private E rightChild(int index) { return items[getRightChildIndex(index)]; }
    // Given an index of a node, get the element of the parent  of that node.
    private E parent(int index) { return items[getParentIndex(index)]; }

    /**      Return the current size of array       **/
    public int getSize(){
        // Modify this function
        return this.size;
    }

    /**      Return true of the size of the heap is 0     **/
    public boolean isEmpty() {
        // Modify this function
        return getSize() == 0;
    }

    /**      Swap the two nodes at the given indexes     **/
    private void swap(int indexOne, int indexTwo) {
        E temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;
    }

    // Resize the array when size is equal to the capacity
    private void ensureExtraCapacity() {
        if ( size == capacity ) {
            items = Arrays.copyOf(items, capacity*2);
            capacity *= 2;
        }
    }

    /**      Returns the min element of the heap (WITHOUT removing it from the heap).     **/
    /**      Throw an IllegalStateException if peek is called on an empty heap     **/
    public E peek() {
        if ( size == 0 ) throw new IllegalStateException();
        return items[0];
    }


    /**      Returns and removes the min element of the heap and re-heapify the whole array.     **/
    /**      Hint: Think how you can use heapifyDown() in this method.     **/
    /**      Throw an IllegalStateException if poll is called on an empty heap     **/
    public E poll() {
        if ( size == 0 ) throw new IllegalStateException();
        E item = items[0];
        items[0] = items[size-1];
        size--;
        heapifyDown();
        return item;
    }

    /**      Add a new element in your heap.     **/
    /**      Hint: What if the array is at max size? Think how you can ensureExtraCapacity to tackle this.     **/
    /**      Hint: Since we always add a new element at the end of a heap, think if we need to call heapifyUp or heapifyDown?     **/
    public void add(E item) {
        ensureExtraCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    // Rebalance the tree starting from last node in the heap.
    public void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && parent(index).compareTo(items[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    // Rebalance the tree starting from root node in the heap.
    public void heapifyDown() {
        int index = 0; // root node
        while ( hasLeftChild(index) ) {
            int smallerChildIndex = getLeftChildIndex(index);
            if ( hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0 ) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if ( items[index].compareTo(items[smallerChildIndex]) < 0) {
                break;
            } else {
                swap(index,smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

}