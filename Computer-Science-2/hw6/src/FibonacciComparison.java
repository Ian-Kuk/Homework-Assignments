import java.lang.System;
import java.util.ArrayList;

public class FibonacciComparison {
    // Write code for recursive fib here
    public static int fibRecursive(int n) {
        if (n <= 1){
            return n;
        }
        return fibRecursive(n-1)+fibRecursive(n-2);
    }

    // Write code iterative fibonacci here
    public static int fibLinear(int n) {
        if (n <= 1){
            return n;
        }
        int fib = 1;
        int fib2 = 1;
        for (int a = 2; a < n; a++){
            int temp = fib;
            fib += fib2;
            fib2 = temp;
        }
        return fib;
    }
}
