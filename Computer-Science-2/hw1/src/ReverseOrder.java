import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
public class ReverseOrder {
    public static int[] ReverseArray(int numElements, int[] intList) {
        //<YOUR CODE HERE>
        int[] reverseList = new int[numElements];
        int numElementsReverse = numElements;
        for (int i = 0; i < numElements; i++) {
            numElementsReverse -= 1 ;
            reverseList[i] = intList[numElementsReverse];
        }
        return reverseList;

    }
    public static void main(String[] args) {
        int numElements;
        int i;
        int[] intList;
        System.out.print("Enter your input: ");
        Scanner input = new Scanner(System.in);

        numElements = input.nextInt();

        intList = new int[numElements];

        for ( i =0; i < numElements; i++)
        {
            intList[i] = input.nextInt();
        }
        intList = ReverseArray(numElements, intList);


        System.out.print("Your output is: " );
        System.out.print(Arrays.toString(intList));



    }
}
