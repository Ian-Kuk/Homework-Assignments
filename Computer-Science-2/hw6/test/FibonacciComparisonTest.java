
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FibonacciComparisonTest {
    FibonacciComparison tester;
    public void init(){
        tester = new FibonacciComparison();
    }

    @Test
    public void outputFile()
    {
      try {
        File output = new File("output.txt");
        Scanner myReader = new Scanner(output);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
        }
        myReader.close();

      } catch (FileNotFoundException e) {
          System.out.println(e);
          System.out.println("File Not Found");
          assertEquals("output.txt", "not found");
      }
    }

    // Testing for Iterative fib
    @Test
    public void testFibLinear_1() {
        assertEquals(0, FibonacciComparison.fibLinear(0));
        assertEquals(5, FibonacciComparison.fibLinear(5));
    }

    // Testing for Recursive fib
    @Test
    public void testFibRecursive_2(){
        assertEquals(5 , tester.fibRecursive(5));
        assertEquals(0, tester.fibRecursive(0));
    }

}


