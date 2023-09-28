import java.util.Objects;

public class Point {
    protected double x, y;
    /*
        Instantiates an x and y value based on its input
    Point class:
        input:
            double x: x position
            double y: y position
     */
    public Point(double x, double y) { this.x =x; this.y = y;}
    /*
        "Getter": returns x position
        output:
            double: x position
     */
    public double getX() { return x; }
    /*
        "Getter": returns y position
        output:
            double: y position
     */
    public double getY() { return y; }
    /*
        returns a new point at its current location
        output:
            Point: a new point at current points location
     */
    public Point getLocation() { return new Point(x,y); }
    /*
        update the position of the current point
        input:
            double x: new x position
            double y: new y position
     */
    public void setLocation(double x, double y) { this.x = x; this.y = y; }
    /*
        moves the point to a new position
        input:
            double dx: how much to move the x coordinate
            double dy: how much to move the y coordinate
     */
    public void translate(double dx, double dy) { x +=dx; y += dy;}
    /*
        checks if this point and another point are the same
        output:
            boolean: if the points are the same
     */
    @Override public boolean equals(Object object) {
        if (object instanceof Point ){
            Point point = (Point)object;
            return (x == point.x) && (y == point.y);
        }
        else
            return false;
    }
    @Override public int hashCode() {
        return Objects.hash(x,y);
    }

    /*
        toString: default string format for this class
        output:
            String: (x,y) (no spaces)
     */
    @Override public String toString(){
        return "(" + x + "," + y + ")";
    }
    public static void main(String[] args) {
        /* create point p = (2,3) using keyword new */
        Point p = new Point(2,3);
        System.out.println("p=" + p);

        /* create point q using p.getLocation() */
        Point q = p.getLocation();
        System.out.println("q="+q);

        /* translate q up 3 and left 1 */
        q.translate(3, -1);
        System.out.println("q="+q);
        
        /* make q refer to p */
        q=p;
        System.out.println("q="+q);

    }
}
