/**
 * Class that will create a touple of the root information
 */
public class Roots{
    public final Double a;
    public final Double b;
    public final String c;
    public final String d;

    /**
     * Constructor that takes in the information and makes it a tuple
     * @param a first root of polynomial
     * @param b second root of polynomial
     * @param c string to see if roots are imaginary or not
     * @param d string that holds what thread solved it
     */
    public Roots(Double a, Double b, String c, String d)
    {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}
