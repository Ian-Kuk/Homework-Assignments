public class Main {


    public static void main(String[] args) {
        Integer[] numbers = {5,32,1,3,5,2,1,6};
        MinHeap<Integer> intHeap = new MinHeap<>();

        // adding values to minHeap
        for (int i = 0; i < numbers.length; i++ )
            intHeap.add(numbers[i]);


        // Min Heap will sort the numbers in descending order
        while (!intHeap.isEmpty())
            System.out.println(intHeap.poll());

    }
}
