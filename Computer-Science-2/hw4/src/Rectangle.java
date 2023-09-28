public class Rectangle implements Polygon{
    private double width;
    private double height;
    public Rectangle(){
        width = 1;
        height = 1;
    }
    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }
    public String toString(){
        return ("Rectangle width and height: " + width +", " + height);
    }
    public double area(){
        return (width * height);
    }

    public double perimeter()
    {
        return ((width*2) + (height * 2));
    }
}
