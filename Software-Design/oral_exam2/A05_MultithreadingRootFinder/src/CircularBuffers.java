import java.util.concurrent.ArrayBlockingQueue;

public class CircularBuffers implements Buffers {

    private final Tuple[] tupleBuffer = {new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0),new Tuple(0,0,0) };
    private final ArrayBlockingQueue<Roots> rootBuffer;
    private static int tupleCellsOccupied = 0;
    private static int tupleWriteIndex = 0;
    private static int tupleReadIndex = 0;
    public static int equations = 0;
    public static int equationsFinished = 0;


    public CircularBuffers(int size)
    {
        rootBuffer = new ArrayBlockingQueue<>(size);
    }

    @Override
    public int returnEquationCreated() {
        return equations;
    }

    @Override
    public int returnEquationsFinished() {
        return equationsFinished;
    }

    /**
     * Synchronized method that puts a Tuple of the three values for the polynomial into a buffer. It increases the equations created and updates how many cells of the buffer are occupied
     * @param tuple the tuple that is to be put in the buffer
     * @throws InterruptedException
     */
    @Override
    public synchronized void blockingPutTuple(Tuple tuple) throws InterruptedException {
        while(tupleCellsOccupied == tupleBuffer.length) //If the buffer is full
        {
            wait();
        }

        tupleBuffer[tupleWriteIndex] = tuple; //put the tuple in the buffer

        tupleWriteIndex = (tupleReadIndex + 1) % tupleBuffer.length; //change the index of where to put the tuple
        ++tupleCellsOccupied; //increase the amount of cells occupied
        equations += 1; // increase amount of equations created
        notifyAll(); //notifys threads waiting
    }

    /**
     * Synchronized method that returns a tuple from the buffer and then decrease the amount of spaces filled inside the buffer.
     * @return returns the tuple that was taken from the buffer
     * @throws InterruptedException
     */
    @Override
    public synchronized Tuple blockingGetTuple() throws InterruptedException {
        while(tupleCellsOccupied == 0)
        {
            wait();
        }
        Tuple equationTuple = tupleBuffer[tupleReadIndex]; //sets a Tuple equal to the tuple from the buffer

        tupleReadIndex = (tupleReadIndex + 1) % tupleBuffer.length; //changes the index to read from

        tupleCellsOccupied--; //decreases the amount of space taken in the buffer
        notifyAll(); //notifys threads waiting

        return equationTuple; //returns the tuple that was taken
    }

    /**
     * Puts the tuple that contains the roots, weather the roots are imaginary, and the thread information into an ArrayBlockingQueue. Also increase the amount of equations finished.
     * @param roots the tuple that contains the roots, if its imaginary, and the thread that completed it.
     * @throws InterruptedException
     */
    @Override
    public void blockingPutRoots(Roots roots) throws InterruptedException {
        equationsFinished++; //increases equations finished
        rootBuffer.put(roots); //puts the tuple in the array
    }

    /**
     * Takes the tuple of the roots, weather its imaginary, and the thread that used it and returns it.
     * @return gives the tuple that contains all the information about the solution to the polynomial
     * @throws InterruptedException
     */
    @Override
    public  Roots blockingGetRoots() throws InterruptedException {
        return rootBuffer.take(); //takes the tuple from the array
    }
}
