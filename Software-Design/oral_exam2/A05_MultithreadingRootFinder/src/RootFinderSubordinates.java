public class RootFinderSubordinates implements Runnable{

    private final Buffers sharedBuffers;
    private final int loopAmount;

    /**
     * Constructor that sets the shared buffer and how many times it should loop
     * @param sharedBuffers the shared buffer between master and subordinate
     * @param loop the amount it should loop
     */
    public RootFinderSubordinates(Buffers sharedBuffers, int loop)
    {
        this.sharedBuffers = sharedBuffers;
        this.loopAmount = loop;
    }

    /**
     * Method that loops the amount of equations that will be created. It gets the tuple from the circular buffer and send it to a method to find the roots
     */
    @Override
    public void run() {
        try
        {
            for (int i =0; i< loopAmount; i++) {
                Tuple tuple = sharedBuffers.blockingGetTuple(); //gets tuple from buffer
                getRoots(tuple.a, tuple.b, tuple.c); //gets the values from the tuple and sends them to a method to get the roots
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method that finds the roots of the polynomial. It takes in the values and will put the roots into another tuple, along with if the roots are imaginary, and what thread did the work on the polynomial
     * @param a first number of polynomial
     * @param b second number of polynomial
     * @param c third number of polynomial
     * @throws InterruptedException
     */
    public void getRoots(int a, int b, int c) throws InterruptedException {
        String imaginary = "no"; //part of tuple that says if its imaginary
        String thread = String.valueOf(Thread.currentThread()); //gets what thread is being used
        double bottom = (2 * a); //bottom of quadratic formula
        double det =(b * b) - (4 * (a * c)); //determinet of quadratic formula

        if(det >= 0) //if the determinate is greater than 0 its not imaginary
        {

            double root1 = (-b + Math.sqrt(det))/ bottom; //gets both roots
            double root2 = (-b - Math.sqrt(det))/ bottom;
            Roots quadraticRoot = new Roots(root1 ,root2,imaginary,thread); //makes a tuple of the information
            sharedBuffers.blockingPutRoots(quadraticRoot); //calls the buffer to put the tuple in the array
        }
        if(det < 0) //if determinate is less than 0 its imaginary
        {
            imaginary = "yes"; //sets the imaginary string to yes
            double root1 = -b / bottom; //root 1 is the real part
            double root2 = Math.sqrt(-det) / bottom; //root 2 is the imaginary part have to do opposite of determinate because it is negative
            Roots quadraticRoot = new Roots(root1 ,root2,imaginary,thread);
            sharedBuffers.blockingPutRoots(quadraticRoot); //calls buffer to put tuple into the array
        }

    }
}
