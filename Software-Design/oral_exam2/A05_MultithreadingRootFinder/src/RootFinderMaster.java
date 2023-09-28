public class RootFinderMaster implements Runnable
{
    private final Buffers sharedBuffers;
    private final int loopAmount;
    private int thread2,thread3,thread4,thread5,thread6,thread7,thread8,thread9,thread10;


    /**
     * Constructor that sets the Buffer that is shared between it and the subordinates. Sets the amount of polynomials the master should create
     * @param sharedBuffers the buffer shared between master and its subordinates
     * @param amount the amount of polynomials it will create
     */
    public RootFinderMaster(Buffers sharedBuffers, int amount)
    {
        this.sharedBuffers = sharedBuffers;
        this.loopAmount = amount;

    }

    /**
     * Method that creates the tuples for every polynomial. The amount it creates depends on the constructor. If it 30 then it will print all the roots it has found.
     * If its 3,000 it will show how many polynomials each thread has solved
     * After the tuple it created it puts it into the buffer, and then tries to get the roots if the equation has been solved
     */
    @Override
    public void run()
    {
        for (int i = 0; i < this.loopAmount; i++)
        {
            Integer numberA = (((int) Math.floor(Math.random() * (1000 + 1000)) - 1000));
            Integer numberB = (((int) Math.floor(Math.random() * (1000 + 1000)) - 1000));
            Integer numberC = (((int) Math.floor(Math.random() * (1000 + 1000)) - 1000));
            Tuple intTuple = new Tuple (numberA, numberB, numberC);
            try
            {
                sharedBuffers.blockingPutTuple(intTuple); //puts the tuple in buffer
                Roots root = sharedBuffers.blockingGetRoots(); //gets the answer to the equation
                if(this.loopAmount == 30) { //if 30 were created
                    if (root.c.equals("yes")) { //checks tuple to see if the roots are imaginary
                        System.out.println("Root 1: " + root.a + " + " + root.b + "i" + " Root 2: " + root.a + " - " + root.b + "i");
                    } else {
                        System.out.println("Root 1: " + root.a + " Root 2: " + root.b);
                    }
                    //If the equations created equal the equations finished, and both are equal to how many equations should have been made it exits
                    if (sharedBuffers.returnEquationsFinished() == sharedBuffers.returnEquationCreated() && sharedBuffers.returnEquationCreated() == this.loopAmount) {
                        System.exit(-1);
                    }
                }
                if(this.loopAmount == 3000) //if its 3,000 polynomials
                {
                    //if statements to increase counters for each thread that was used
                    //The thread that was used is stored in the tuple
                    if (root.d.equals("Thread[pool-1-thread-2,5,main]"))
                    {
                        thread2 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-3,5,main]"))
                    {
                        thread3 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-4,5,main]"))
                    {
                        thread4 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-5,5,main]"))
                    {
                        thread5 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-6,5,main]"))
                    {
                        thread6 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-7,5,main]"))
                    {
                        thread7 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-8,5,main]"))
                    {
                        thread8 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-9,5,main]"))
                    {
                        thread9 +=1;
                    }
                    if (root.d.equals("Thread[pool-1-thread-10,5,main]"))
                    {
                        thread10 +=1;
                    }


                }
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        //prints out what each thread has done if the loop is done 3000 times and if the sum of all thread equal 3000
        if (this.loopAmount == 3000 && (thread10 + thread9 +thread8 + thread7+ thread6 + thread5 + thread4 + thread3 + thread2) == 3000)
        {
            System.out.println("Thread 2 solved " + thread2 + " polynomials");
            System.out.println("Thread 3 solved " + thread3 + " polynomials");
            System.out.println("Thread 4 solved " + thread4 + " polynomials");
            System.out.println("Thread 5 solved " + thread5 + " polynomials");
            System.out.println("Thread 6 solved " + thread6 + " polynomials");
            System.out.println("Thread 7 solved " + thread7 + " polynomials");
            System.out.println("Thread 8 solved " + thread8 + " polynomials");
            System.out.println("Thread 9 solved " + thread9 + " polynomials");
            System.out.println("Thread 10 solved " + thread10 + " polynomials");
            System.exit(0);
        }
    }
}

