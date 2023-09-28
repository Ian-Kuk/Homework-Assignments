import java.util.Objects;

public class ColoredPoint extends Point {
    private String color = "black";

    public ColoredPoint(double x, double y, String color) {
        super(x,y);
        // this method creates a point with (x,y,color)
        //code here; //use the keyword super to set coordinates (x,y)
        setColor(color);
        //code here; //use the keyword this to set the color

       // setColor(this.color);
    }

    public ColoredPoint(Point p, String color) {
       // this method adds a color to a point p (p.x,p.y,color)
        super(p.x,p.y);
        //code here; //use the keyword super to set coordinates (x,y) for p
        setColor(color);
        //code here; //use the keyword this to set the color
    }

    public String getColor() { return this.color; }
    public void   setColor(String color) { this.color = color; }

    @Override public boolean equals(Object o) {
        if ( o instanceof ColoredPoint ) {
            ColoredPoint p = (ColoredPoint)o;
            return x == p.x && y == p.y && color == p.color;
        }
        else
            return false;
    }

    // if we override equals() we have to override hashCode()
    @Override public int hashCode() {
        return Objects.hash(x, y, color);
    }

    @Override public String toString() {
        // string returned should be "(x,y,color)"
        // where x, y are the co-ordinates and color is the color.
        return ("(" + x + "," + y + "," + color + ")");
    }

    public static void main(String[] args) {
        ColoredPoint p = new ColoredPoint(2,3,"red");
        System.out.println("p = " + p);
        /* create point q using getLocation() */
        Point q = p.getLocation();
        /* q and p will have the same co-ordinates, but q has no color */
        System.out.println("q="+q);
        System.out.println("p equal to q?" + p.equals(q));
        ColoredPoint q2 = new ColoredPoint(q,"red");
        System.out.println("q2="+q2);
        System.out.println("q2 equal to p? "+p.equals(q2));

        q2.translate(3, -1); /* p and q have different values */
        System.out.println("q2 = "+q2);
        System.out.println("q2 equal to p? "+p.equals(q2));

        q2 = p; /* now q2 and p are the same (p==q) */
        System.out.println("q2 = "+q2);
        System.out.println("q2 equal to p? "+p.equals(q2));

        q2.setColor("blue");
        System.out.println("q2 = "+q2);
        System.out.println("p  = "+p);
        System.out.println("q2 equal to p? "+p.equals(q2));
    }
}