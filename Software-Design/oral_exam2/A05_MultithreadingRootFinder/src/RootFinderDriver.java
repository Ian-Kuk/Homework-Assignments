import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;
public class RootFinderDriver {
    /**
     * Main driver that runs RootFinderMaster and RootFinderSubordinates. Creates the thread pool and creates the threads that will handle the polynomials.
     * @param args
     */
    public static void main(String[] args) {
        boolean exit = false;
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Scanner input = new Scanner(System.in);
        while(!exit) {
            System.out.println("Type 1 to get the roots of 30 numbers");
            System.out.println("Type 2 to get the roots of 3000 numbers");
            System.out.println("Type Exit to exit");
            String test = input.next();
            if(test.equalsIgnoreCase("1"))
            {
                CircularBuffers sharedBufferValues = new CircularBuffers(30);
                executorService.execute(new RootFinderMaster(sharedBufferValues,  30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,30));

                executorService.shutdown();

            }
            if(test.equalsIgnoreCase("2"))
            {
                CircularBuffers sharedBufferValues = new CircularBuffers(3000);
                executorService.execute(new RootFinderMaster(sharedBufferValues, 3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));
                executorService.execute(new RootFinderSubordinates(sharedBufferValues,3000));


                executorService.shutdown();


            }
            if(input.next().equalsIgnoreCase("Exit"))
            {
                exit = true;
            }
            else
            {
                System.out.println("Invalid choice");

            }
        }




    }
}
//basically the master class does not need to be threaded at all
//make 2 circular buffers
//master puts numebrs in one buffer that is shared with slaves
//slaves take the number and do quadratic equations
//slaves output to second buffer that the master prints out
