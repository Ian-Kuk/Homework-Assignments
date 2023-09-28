public class Main {
    public static void main(String[] args) {
        // list of fibonacci sequence numbers
        int[] nlist = { 5,10, 15, 20, 25, 30, 35};
        // Two arrays (one for fibLinear, other for fibRecursive) to store time for each run.
        // There are a total of nlist.length inputs that we will test
        double[] timingsEF = new double[nlist.length];
        double[] timingsLF = new double[nlist.length];

        // Every number in n_list will be given as input 5 times to both fibonacci functions
        // and an average will be taken to make the results more accurate.
        int numTrials = 5;

        //Iterating over number list
        for ( int i = 0; i < nlist.length; i++ ) {
            int n = nlist[i];
            // Start recording time
            long start = System.nanoTime();
            // Run fibRecursive function 5 times
            for ( int k = 0; k < numTrials; k++ )
                FibonacciComparison.fibRecursive(n);
            // Stop time
            long stop = System.nanoTime();
            // Take average of the run time and store it in the array
            timingsEF[i] = 1e-9*(stop-start) / numTrials;

            // Start time
            start = System.nanoTime();
            // Run FibLinear 5 times
            for ( int k = 0; k < numTrials; k++ )
                FibonacciComparison.fibLinear(n);
            // Stop time
            stop = System.nanoTime();
            //Take average and store it in the array
            timingsLF[i] = 1e-9*(stop-start) / numTrials;
        }

        // Print out the runtimes for different fib functions.
        System.out.println("\n\nTimings for Exponential Fibonacci");
        for ( double time : timingsEF )
            System.out.print("  "+time);
        System.out.println("\n\n");
        System.out.println("Timings for Linear Fibonacci");
        for ( double time : timingsLF )
            System.out.print("  "+time);
        System.out.println();
    }
}
