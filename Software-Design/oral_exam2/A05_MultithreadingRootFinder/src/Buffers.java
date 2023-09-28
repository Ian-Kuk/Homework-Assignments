/**
 * Interface with methods that will be inherited by the circular buffer class
 */
public interface Buffers {

    public void blockingPutTuple(Tuple tuple) throws InterruptedException;

    public Tuple blockingGetTuple() throws InterruptedException;

    public void blockingPutRoots(Roots roots) throws InterruptedException;

    public Roots blockingGetRoots() throws InterruptedException;

    public int returnEquationCreated();
    public int returnEquationsFinished();
}
